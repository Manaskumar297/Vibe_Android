package com.manas.vibe.feature.auth.login.presentation

import androidx.lifecycle.ViewModel
import com.manas.vibe.feature.auth.login.data.PhoneNumberValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

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

                    _uiState.update {
                        it.copy(
                            errorMessage = null,
                            isLoading = true
                        )
                    }

                    // OTP logic will come here
                }
            }
        }
    }
}