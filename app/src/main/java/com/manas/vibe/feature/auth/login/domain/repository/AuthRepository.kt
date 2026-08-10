package com.manas.vibe.feature.auth.login.domain.repository

import android.app.Activity
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential

interface  AuthRepository{
    fun getCurrentUser(): FirebaseUser?
    fun sendOtp(
        phoneNumber: String,
        activity: Activity,
        onCodeSent:(verificationId:String)-> Unit,
        onVerificationCompleted:(PhoneAuthCredential)->Unit,
        onVerificationFailed:(Exception)-> Unit
    )
    fun verifyOtp(
        verificationId: String,
        otp: String,
        onSuccess: (FirebaseUser) -> Unit,
        onFailure: (Exception) -> Unit
    )
}