package com.manas.vibe.feature.auth.data.repository

import com.manas.vibe.feature.auth.data.remote.AuthRemoteDataSource
import com.manas.vibe.feature.auth.domain.model.User
import com.manas.vibe.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {

    override fun getCurrentUser(): User? {
        return authRemoteDataSource.getCurrentUser()
    }

    override fun loginWithEmail(
        email: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        authRemoteDataSource.loginWithEmail(
            email = email,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    override fun verifyOtp(
        verificationId: String,
        otp: String,
        onSuccess: (User) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        authRemoteDataSource.verifyOtp(
            verificationId = verificationId,
            otp = otp,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }
}
