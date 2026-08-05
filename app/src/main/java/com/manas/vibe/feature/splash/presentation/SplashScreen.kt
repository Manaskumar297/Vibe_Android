package com.manas.vibe.feature.splash.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manas.vibe.R
import com.manas.vibe.ui.theme.Dimens

@Composable
fun SplashScreen(
    onNavigateToLogin:()-> Unit,
    onNavigateToHome:()-> Unit,
    viewModel: SplashViewModel= viewModel()
){
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event->
            when(event){
                SplashUiEvent.NavigateToLogin->{
                    onNavigateToLogin()
                }
                SplashUiEvent.NavigateToHome->{
                    onNavigateToHome()
                }
            }
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = "Vibe Logo",
            modifier = Modifier
                .size(Dimens.splashLogoSize)
                .align(Alignment.Center)
        )
        Text(
            text = "Connect . Share . Vibe",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = Dimens.splashBottomPadding)




        )
    }
}