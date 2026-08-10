package com.manas.vibe.feature.auth.login.presentation

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import com.manas.vibe.feature.auth.login.data.PhoneNumberValidator
import com.manas.vibe.feature.auth.login.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private  val authRepository: AuthRepository
)  : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val phoneNumberValidator = PhoneNumberValidator()

    fun onEvent(event: LoginUiEvent) {

        when (event) {

            is LoginUiEvent.PhoneNumberChanged -> {
                _uiState.update {
                    it.copy(
                        phoneNumber = event.phoneNumber,
                        errorMessage = null
                    )
                }
            }

            is LoginUiEvent.CountryChanged -> {
                _uiState.update {
                    it.copy(
                        selectedCountry = event.country,
                        errorMessage = null
                    )
                }
            }

            LoginUiEvent.ContinueClicked -> {

                val state = _uiState.value

                val isValid = phoneNumberValidator.validate(
                    phoneNumber = state.phoneNumber,
                    regionCode = state.selectedCountry.isoCode
                )

                if (!isValid) {

                    _uiState.update {
                        it.copy(
                            errorMessage = "Please enter a valid phone number."
                        )
                    }

                } else {


                    // OTP logic will come here
                }
            }

            is LoginUiEvent.OtpSent -> {
                Log.d(
                    "LoginViewModel",
                    "OTP sent. Verification ID received."
                )
                _uiState.update {
                    it.copy(
                        verificationId  = event.verificationId,
                        isLoading = false,
                        errorMessage = null,
                        navigateToOtp=true
                    )
                }
            }

            is LoginUiEvent.VerificationFailed -> {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = event.message
                    )
                }
            }
            is LoginUiEvent.NavigationHandled -> {
                _uiState.update {
                    it.copy(navigateToOtp = false)
                }
            }
        }
    }
    fun sendOtp(activity: Activity) {

        val state = _uiState.value

        val phoneNumber =
            state.selectedCountry.dialCode + state.phoneNumber

        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }

        authRepository.sendOtp(
            phoneNumber = phoneNumber,
            activity = activity,

            onCodeSent = { verificationId ->
                onEvent(
                    LoginUiEvent.OtpSent(verificationId)
                )
            },

            onVerificationCompleted = {
                // We'll handle automatic verification next
            },

            onVerificationFailed = { exception ->
                onEvent(
                    LoginUiEvent.VerificationFailed(
                        exception.message ?: "Failed to send OTP"
                    )
                )
            }
        )
    }
}