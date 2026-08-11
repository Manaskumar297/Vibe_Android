package com.manas.vibe.feature.auth.otp.presentation

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.manas.vibe.R
import com.manas.vibe.ui.components.OtpTextField
import com.manas.vibe.ui.components.VibeButton

@Composable
fun OtpScreen(
    verificationId:String,
    viewModel: OtpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    OtpContent(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        onVerify = {
            // Safe handling if verificationId is available in your UI state
            viewModel.verifyOtp(verificationId)
        }
    )
}

@Composable
private fun OtpContent(
    uiState: OtpUiState,
    onEvent: (OtpUiEvent) -> Unit,
    onVerify: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image with matching opacity effect
        Image(
            painter = painterResource(id = R.drawable.img_background),
            contentDescription = null,
            alpha = 0.5f,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 0.5f),
            contentScale = ContentScale.Crop
        )

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
                text = "Verify your number",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enter the 6-digit code sent to your phone",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Integrated 6-Digit OTP Field component
            OtpTextField(
                otpText = uiState.otp, // Ensure your OtpUiState has an `otpCode` field
                onOtpTextChange = { code, isComplete ->
                    onEvent(OtpUiEvent.OtpChanged(code))
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            uiState.errorMessage?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            VibeButton(
                text = "Verify",
                isLoading = uiState.isLoading,
                onClick = onVerify,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OtpScreenPreview() {
    OtpContent(
        uiState = OtpUiState(
            otp = "123456",
            isLoading = false,
            errorMessage = null,
            isVerified = false
        ),
        onEvent = {},
        onVerify = {}
    )
}

