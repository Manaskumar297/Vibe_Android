package com.manas.vibe.feature.auth.data.remote

import com.manas.vibe.feature.auth.domain.model.User
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor() {

    fun getCurrentUser(): User? {
        // Placeholder: Implement local storage or token check here
        return null
    }

    fun loginWithEmail(
        email: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        // Mock API Call
        onSuccess("mock_token_$email")
    }

    fun verifyOtp(
        verificationId: String,
        otp: String,
        onSuccess: (User) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        // Mock API Call
        if (otp == "123456") {
            onSuccess(User(id = "user123", email = "test@example.com"))
        } else {
            onFailure(Exception("Invalid OTP"))
        }
    }
}
