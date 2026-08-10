package com.manas.vibe.feature.auth.otp.presentation

import androidx.lifecycle.ViewModel
import com.manas.vibe.feature.auth.login.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class OtpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(OtpUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: OtpUiEvent) {
        when (event) {
            is OtpUiEvent.OtpChanged -> {
                _uiState.update {
                    it.copy(
                        otp = event.otp
                    )
                }
            }

            is OtpUiEvent.VerifyClicked -> {

            }

            is OtpUiEvent.ResendClicked -> {}
        }
    }

    fun verifyOtp(verificationId: String) {
        val otp = _uiState.value.otp
        if (otp.length != 6) {
            _uiState.update { it.copy(errorMessage = "Please enter a valid 6-digit OTP.") }
            return
        }
        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }
        authRepository.verifyOtp(
            verificationId = verificationId, otp = otp, onSuccess = {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isVerified = true,
                        errorMessage = null
                    )
                }
            },
            onFailure = { exception ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Invalid OTP. Please try again."
                    )
                }
            })
    }
}