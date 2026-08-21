package com.manas.vibe.feature.auth.login.data

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.FirebaseUser
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FirebaseAuthDataSource @Inject constructor() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun sendOtp(
        phoneNumber: String,
        activity: Activity,
        onSuccess: (verificationId: String) -> Unit,
        onAutoVerify: (PhoneAuthCredential) -> Unit,
        onFailed: (Exception) -> Unit
    ) {

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(
                object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    override fun onVerificationCompleted(
                        credential: PhoneAuthCredential
                    ) {
                        onAutoVerify(credential)
                    }

                    override fun onVerificationFailed(
                        exception: com.google.firebase.FirebaseException
                    ) {
                        onFailed(exception)
                    }

                    override fun onCodeSent(
                        verificationId: String,
                        token: PhoneAuthProvider.ForceResendingToken
                    ) {
                        onSuccess(verificationId)
                    }
                }
            )
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyOtp(
        verificationId: String,
        otp: String,
        onSuccess: (FirebaseUser) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val credential = PhoneAuthProvider.getCredential(
            verificationId,
            otp
        )

        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener { result ->
                result.user?.let { user ->
                    onSuccess(user)
                } ?: onFailure(
                    IllegalStateException("User is null after verification")
                )
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
}