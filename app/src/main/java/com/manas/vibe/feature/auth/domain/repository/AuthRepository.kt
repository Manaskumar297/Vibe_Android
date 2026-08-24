package com.manas.vibe.feature.auth.domain.repository

import com.manas.vibe.feature.auth.domain.model.User

interface AuthRepository {
    fun getCurrentUser(): User?
    
    fun loginWithEmail(
        email: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    )

    fun verifyOtp(
        verificationId: String,
        otp: String,
        onSuccess: (User) -> Unit,
        onFailure: (Exception) -> Unit
    )
}
