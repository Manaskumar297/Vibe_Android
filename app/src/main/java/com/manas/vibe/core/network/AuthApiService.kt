package com.manas.vibe.core.network

import retrofit2.http.GET

interface AuthApiService {

    @GET("")
    suspend fun SendVerificationOtp():String
}