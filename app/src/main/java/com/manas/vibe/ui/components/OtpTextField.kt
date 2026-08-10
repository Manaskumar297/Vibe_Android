package com.manas.vibe.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpTextField(
    otpText: String,
    onOtpTextChange: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    length: Int = 6
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until length) {
            val char = when {
                i < otpText.length -> otpText[i].toString()
                else -> ""
            }

            OutlinedTextField(
                value = char,
                onValueChange = { value ->
                    if (value.length <= 1) {
                        val currentText = StringBuilder(otpText)
                        if (i < currentText.length) {
                            if (value.isNotEmpty()) {
                                currentText[i] = value[0]
                            } else {
                                currentText.deleteCharAt(i)
                            }
                        } else {
                            if (value.isNotEmpty()) {
                                currentText.append(value[0])
                            }
                        }
                        onOtpTextChange(currentText.toString(), currentText.length == length)
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                textStyle = androidx.compose.ui.text.TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                ),
                modifier = Modifier
                    .width(48.dp)
                    .height(56.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OtpTextFieldPreview() {
    OtpTextField(
        otpText = "123",
        onOtpTextChange = { _, _ -> }
    )
}

@Preview(showBackground = true)
@Composable
private fun OtpTextFieldCompletePreview() {
    OtpTextField(
        otpText = "123456",
        onOtpTextChange = { _, _ -> }
    )
}

@Preview(showBackground = true)
@Composable
private fun OtpTextFieldEmptyPreview() {
    OtpTextField(
        otpText = "",
        onOtpTextChange = { _, _ -> }
    )
}

