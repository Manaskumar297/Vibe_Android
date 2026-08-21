package com.manas.vibe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manas.vibe.R

@Composable
fun OtpTextField(
    otpText: String,
    onOtpTextChange: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    length: Int = 6
) {
    BasicTextField(
        value = otpText,
        onValueChange = {
            if (it.length <= length && it.all { char -> char.isDigit() }) {
                onOtpTextChange(it, it.length == length)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier.fillMaxWidth(),
        cursorBrush = SolidColor(Color.Transparent),
        textStyle = TextStyle(color = Color.Transparent),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                innerTextField()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(length) { index ->
                        val char = when {
                            index < otpText.length -> otpText[index].toString()
                            else -> ""
                        }
                        OtpBox(char = char)
                    }
                }
            }
        }
    )
}

@Composable
private fun OtpBox(char: String) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(R.color.FFFFFF)),
        contentAlignment = Alignment.Center
    ) {
        if (char.isEmpty()) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF9E9E9E)) // Neutral grey dot
            )
        } else {
            Text(
                text = char,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = colorResource(R.color._1E3C72)
                )
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFC1D2FF)
@Composable
private fun OtpTextFieldPreview() {
    OtpTextField(
        otpText = "48",
        onOtpTextChange = { _, _ -> }
    )
}
