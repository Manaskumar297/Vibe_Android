package com.manas.vibe.feature.auth.domain.model

data class User(
    val id: String,
    val email: String?,
    val phoneNumber: String? = null,
    val displayName: String? = null
)
