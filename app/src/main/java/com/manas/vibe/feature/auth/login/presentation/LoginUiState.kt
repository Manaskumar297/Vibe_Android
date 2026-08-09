package com.manas.vibe.feature.auth.login.presentation

import com.manas.vibe.feature.auth.login.data.countries
import com.manas.vibe.feature.auth.login.domain.model.Country

data class LoginUiState(
    val selectedCountry: Country= countries.first(),
    val phoneNumber: String="",
    val isLoading: Boolean=false,
    val errorMessage:String?=null
)