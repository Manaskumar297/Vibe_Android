package com.manas.vibe.feature.auth.login.presentation

import com.manas.vibe.feature.auth.data.countries
import com.manas.vibe.feature.auth.domain.model.Country

data class LoginUiState(
    val selectedCountry: Country= countries.first(),
    val phoneNumber: String="",
    val email: String="",
    val isLoading: Boolean=false,
    val errorMessage:String?=null,
    val verificationId: String? = null,
    val navigateToOtp: Boolean = false

)