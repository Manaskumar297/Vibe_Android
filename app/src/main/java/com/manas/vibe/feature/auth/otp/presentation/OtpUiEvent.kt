package com.manas.vibe.feature.auth.otp.presentation

sealed interface OtpUiEvent{
    data class OtpChanged(
        val otp: String
    ): OtpUiEvent
    data object  VerifyClicked: OtpUiEvent
    data object ResendClicked: OtpUiEvent
}