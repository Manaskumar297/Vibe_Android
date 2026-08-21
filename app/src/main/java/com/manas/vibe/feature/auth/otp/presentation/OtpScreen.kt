package com.manas.vibe.feature.auth.otp.presentation

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.manas.vibe.R
import com.manas.vibe.ui.components.BoxImgCenter
import com.manas.vibe.ui.components.BoxTextCenter
import com.manas.vibe.ui.components.OtpTextField
import com.manas.vibe.ui.util.MultiClickableText
import com.manas.vibe.ui.util.TextSegment

@Composable
fun OtpScreen(
    verificationId: String,
    onNavigateToHome: () -> Unit,
    viewModel: OtpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isVerified) {
        if (uiState.isVerified) {
            onNavigateToHome()
        }
    }

    OtpContent(
        uiState = uiState,
        onEvent = viewModel::onEvent,
        onVerify = {
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.C1D2FF))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
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
                text = "Verify your phone\nnumber",
                modifier = Modifier.padding(top = 32.dp)
            )

            Text(
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                ),
                color = colorResource(R.color._485E98),
                text = "We've sent a code to your phone",
                modifier = Modifier.padding(top = 12.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            OtpTextField(
                otpText = uiState.otp,
                onOtpTextChange = { code, isComplete ->
                    onEvent(OtpUiEvent.OtpChanged(code))
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            BoxTextCenter(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = "Verify",
                backgroundColor = R.color._1E3C72,
                textColor = R.color.FFFFFF,
                cornerRadios = 16.dp,
                textPaddingValues = PaddingValues(vertical = 18.dp),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                onClick = onVerify
            )

            if (uiState.isLoading) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Verifying...",
                    modifier = Modifier.padding(top = 8.dp),
                    color = colorResource(R.color._1E3C72)
                )
            }

            uiState.errorMessage?.let { error ->
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            MultiClickableText(
                segment = listOf(
                    TextSegment(
                        text = "Didn't receive the code? ",
                        color = colorResource(R.color._485E98)
                    ),
                    TextSegment(
                        text = "Resend Code",
                        color = colorResource(R.color._1E3C72),
                        fontWeight = FontWeight.Bold,
                        isClickable = true,
                        onClick = { /* TODO: Resend OTP */ }
                    )
                ),
                baseTextStyle = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OtpScreenPreview() {
    OtpContent(
        uiState = OtpUiState(
            otp = "123",
            isLoading = false,
            errorMessage = null,
            isVerified = false
        ),
        onEvent = {},
        onVerify = {}
    )
}
