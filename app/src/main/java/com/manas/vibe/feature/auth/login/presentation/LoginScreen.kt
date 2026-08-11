package com.manas.vibe.feature.auth.login.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.manas.vibe.R
import com.manas.vibe.ui.components.PhoneNumberField
import com.manas.vibe.ui.components.VibeButton
import com.manas.vibe.ui.util.findActivity

@Composable
fun LoginScreen(
    onNavigateToOtp: (String) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    val activity = context.findActivity()
    LaunchedEffect(uiState.navigateToOtp) {
        if (uiState.navigateToOtp) {
            onNavigateToOtp(uiState.verificationId)

            // Tell ViewModel that navigation has been consumed
            viewModel.onEvent(LoginUiEvent.NavigationHandled)
        }
    }
    LoginContent(
        uiState = uiState,
        onEvent = { event ->

            viewModel.onEvent(event)

            if (event is LoginUiEvent.ContinueClicked) {
                activity?.let {
                    viewModel.sendOtp(it)
                }
            }
        }
    )
}

@Composable
private fun LoginContent(
    uiState: LoginUiState,
    onEvent: (LoginUiEvent) -> Unit
) {
    // Outer Box to hold the full-screen background image
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image with explicit alpha and graphicsLayer
        Image(
            painter = painterResource(id = R.drawable.img_background),
            contentDescription = null,
            alpha = 0.5f,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 0.4f),
            contentScale = ContentScale.Crop
        )

        // Foreground Content Layout
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.app_icon),
                contentDescription = "Vibe Logo",
                modifier = Modifier.size(90.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Welcome to Vibe",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enter your phone number to continue",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(32.dp))

            PhoneNumberField(
                selectedCountry = uiState.selectedCountry,
                phoneNumber = uiState.phoneNumber,

                onPhoneNumberChange = {
                    onEvent(
                        LoginUiEvent.PhoneNumberChanged(it)
                    )
                },

                onCountryChange = {
                    onEvent(
                        LoginUiEvent.CountryChanged(it)
                    )
                },

                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            VibeButton(
                text = "Continue",
                isLoading = uiState.isLoading,
                onClick = {
                    onEvent(LoginUiEvent.ContinueClicked)
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginContent(
        uiState = LoginUiState(),
        onEvent = {}
    )
}