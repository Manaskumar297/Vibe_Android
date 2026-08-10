package com.manas.vibe.feature.auth.login.presentation

import com.manas.vibe.feature.auth.login.domain.model.Country

sealed interface LoginUiEvent {

    data class PhoneNumberChanged(
        val phoneNumber: String
    ) : LoginUiEvent

    data class CountryChanged(
        val country: Country
    ) : LoginUiEvent

    data object ContinueClicked : LoginUiEvent
}