package com.manas.vibe.feature.auth.login.presentation

data class LoginUiState(
    val countryCode: String = "+91",
    val phoneNumber: String="",
    val isLoading: Boolean=false,
    val errorMessage:String?=null
)