package com.manas.vibe.feature.splash.presentation

sealed interface SplashUiEvent{
    data object NavigateToLogin: SplashUiEvent
    data object NavigateToHome: SplashUiEvent
}