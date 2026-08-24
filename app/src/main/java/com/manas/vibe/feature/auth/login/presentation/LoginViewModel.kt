package com.manas.vibe.feature.auth.login.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.manas.vibe.feature.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.EmailChanged -> {
                _uiState.update {
                    it.copy(
                        email = event.email,
                        errorMessage = null
                    )
                }
            }

            LoginUiEvent.ContinueClicked -> {
                Log.d("LoginViewModel", "ContinueClicked")
                val state = _uiState.value
                val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(state.email).matches()

                if (!isEmailValid) {
                    _uiState.update {
                        it.copy(errorMessage = "Please enter a valid email address.")
                    }
                } else {
                    _uiState.update { it.copy(errorMessage = null) }
                    loginWithEmail(state.email)
                }
            }

            is LoginUiEvent.NavigationHandled -> {
                _uiState.update {
                    it.copy(navigateToOtp = false)
                }
            }

            else -> {}
        }
    }

    private fun loginWithEmail(email: String) {
        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }

        authRepository.loginWithEmail(
            email = email,
            onSuccess = { token ->
                Log.d("LoginViewModel", "Login success: $token")
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        navigateToOtp = true,
                        verificationId = token
                    )
                }
            },
            onFailure = { exception ->
                Log.e("LoginViewModel", "Login failed", exception)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to log in"
                    )
                }
            }
        )
    }
}
