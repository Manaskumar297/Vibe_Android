package com.manas.vibe.feature.auth.login.data.repository

import android.app.Activity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.manas.vibe.feature.auth.login.data.FirebaseAuthDataSource
import com.manas.vibe.feature.auth.login.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuthDataSource: FirebaseAuthDataSource
) : AuthRepository {

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuthDataSource.getCurrentUser()
    }

    override fun sendOtp(
        phoneNumber: String,
        activity: Activity,
        onCodeSent: (verificationId: String) -> Unit,
        onVerificationCompleted: (PhoneAuthCredential) -> Unit,
        onVerificationFailed: (Exception) -> Unit
    ) {
        firebaseAuthDataSource.sendOtp(
            phoneNumber = phoneNumber,
            activity = activity,
            onSuccess = onCodeSent,
            onAutoVerify = onVerificationCompleted,
            onFailed = onVerificationFailed
        )
    }
    override fun verifyOtp(
        verificationId: String,
        otp: String,
        onSuccess: (FirebaseUser) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        firebaseAuthDataSource.verifyOtp(
            verificationId = verificationId,
            otp = otp,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

}