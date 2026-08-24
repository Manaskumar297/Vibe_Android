package com.manas.vibe.feature.auth.login.presentation

import com.manas.vibe.feature.auth.domain.model.Country

sealed interface LoginUiEvent {

    data class PhoneNumberChanged(
        val phoneNumber: String
    ) : LoginUiEvent

    data class EmailChanged(
        val email: String
    ) : LoginUiEvent

    data class CountryChanged(
        val country: Country
    ) : LoginUiEvent

    data object ContinueClicked : LoginUiEvent
    data class OtpSent(
        val verificationId: String
    ) : LoginUiEvent

    data class VerificationFailed(
        val message: String
    ) : LoginUiEvent

    data object NavigationHandled : LoginUiEvent
}