package com.manas.vibe.core.network

import com.manas.vibe.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun  provideLogingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply {
            level= HttpLoggingInterceptor.Level.BODY
        }
    }
    @Provides
    @Singleton
    fun provideOkhttpClient(
        logingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor (logingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(
        retrofit: Retrofit
    ): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

}