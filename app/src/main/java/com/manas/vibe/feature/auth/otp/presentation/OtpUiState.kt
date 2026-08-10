package com.manas.vibe.feature.auth.otp.presentation

data class OtpUiState(
    val otp: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isVerified: Boolean = false,
    val verificationId: String = ""
)