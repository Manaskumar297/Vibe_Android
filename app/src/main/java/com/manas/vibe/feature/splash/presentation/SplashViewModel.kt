package com.manas.vibe.feature.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class SplashViewModel: ViewModel(){
    private val _uiState= MutableStateFlow(SplashUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent= MutableSharedFlow<SplashUiEvent>()
    val  uiEvent=_uiEvent.asSharedFlow()

    init {
        checkUserSession()
    }

    private fun checkUserSession() {
        viewModelScope.launch {
            delay(2.seconds)
            _uiEvent.emit(
                SplashUiEvent.NavigateToLogin
            )
        }
    }
}