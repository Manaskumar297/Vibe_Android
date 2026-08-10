package com.manas.vibe.di

import com.manas.vibe.feature.auth.login.data.repository.AuthRepositoryImpl
import com.manas.vibe.feature.auth.login.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    @Singleton
    abstract fun  bindAuthRepository(
        implementation: AuthRepositoryImpl
    ): AuthRepository

}