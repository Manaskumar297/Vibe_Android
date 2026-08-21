package com.manas.vibe.feature.auth.login.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.manas.vibe.R
import com.manas.vibe.ui.components.BoxImgCenter
import com.manas.vibe.ui.components.BoxTextCenter
import com.manas.vibe.ui.components.PhoneNumberField
import com.manas.vibe.ui.util.MultiClickableText
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
            uiState.verificationId?.let { verificationId ->
                onNavigateToOtp(verificationId)
            }

            viewModel.onEvent(LoginUiEvent.NavigationHandled)
        }
    }
    LoginContent(
        uiState = uiState,
        onEvent = { event ->
            Log.d("LoginScreen", "Event triggered: $event")
            viewModel.onEvent(event)

            if (event is LoginUiEvent.ContinueClicked) {
                if (activity != null) {
                    Log.d("LoginScreen", "Starting OTP send...")
                    viewModel.sendOtp(activity)
                } else {
                    Log.e("LoginScreen", "Activity is NULL - cannot send OTP")
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.C1D2FF))

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            BoxImgCenter(
                centerImage = R.drawable.icon_arrow_left,
                modifier = Modifier.size(48.dp),
                backgroundColorRes = R.color.FFFFFF,
                cornerRadius = 16.dp,
                contentDescription = "backBtn"
            )
            Text(
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                color = colorResource(R.color._1E3C72),
                text = "Enter your phone\nnumber",
                modifier = Modifier.padding(top = 32.dp)
            )
            Text(
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                ),
                color = colorResource(R.color._485E98),
                text = "Vibe will send an SMS message to verify\nyour phone number.",
                modifier = Modifier.padding(top = 12.dp)


            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )
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
            Spacer(
                modifier = Modifier.fillMaxWidth()
                    .height(24.dp)
            )
            BoxTextCenter(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = if (uiState.isLoading) "Sending..." else "Send Verification Code",
                backgroundColor = if (uiState.isLoading) R.color._6F83C1 else R.color._1E3C72,
                textColor = R.color.FFFFFF,
                cornerRadios = 16.dp,
                textPaddingValues = PaddingValues(vertical = 18.dp),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                onClick = {
                    if (!uiState.isLoading) {
                        onEvent(LoginUiEvent.ContinueClicked)
                    }
                }
            )

            uiState.errorMessage?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            MultiClickableText(
                segment = listOf(
                    com.manas.vibe.ui.util.TextSegment(
                        text = "By continuing, you agree to our ",
                        color = colorResource(R.color._6F83C1)
                    ),
                    com.manas.vibe.ui.util.TextSegment(
                        text = "Terms and Conditions",
                        color = colorResource(R.color._1E3C72),
                        fontWeight = FontWeight.Bold,
                        isClickable = true,
                        onClick = { /* TODO: Navigate to Terms */ }
                    ),
                    com.manas.vibe.ui.util.TextSegment(
                        text = " and ",
                        color = colorResource(R.color._6F83C1)
                    ),
                    com.manas.vibe.ui.util.TextSegment(
                        text = "Privacy Policy",
                        color = colorResource(R.color._1E3C72),
                        fontWeight = FontWeight.Bold,
                        isClickable = true,
                        onClick = { /* TODO: Navigate to Privacy */ }
                    )
                ),
                baseTextStyle = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview2() {
    var uiState by remember { mutableStateOf(LoginUiState()) }

    LoginContent(
        uiState = uiState,
        onEvent = { event ->
            when (event) {
                is LoginUiEvent.PhoneNumberChanged -> {
                    uiState = uiState.copy(phoneNumber = event.phoneNumber)
                }
                is LoginUiEvent.CountryChanged -> {
                    uiState = uiState.copy(selectedCountry = event.country)
                }
                LoginUiEvent.ContinueClicked -> {
                    uiState = uiState.copy(isLoading = true)
                }
                else -> {}
            }
        }
    )
}
