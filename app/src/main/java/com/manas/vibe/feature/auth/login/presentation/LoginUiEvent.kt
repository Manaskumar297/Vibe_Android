package com.manas.vibe.feature.auth.login.presentation

sealed interface LoginUiEvent{
    data class PhoneNumberChanged(
        val phoneNumber:String
    ): LoginUiEvent
    data object ContinueClicked: LoginUiEvent
}