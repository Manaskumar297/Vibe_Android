package com.manas.vibe.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VibeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    enable: Boolean = true,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = placeholder)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        enabled = enable,
        singleLine = singleLine,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun VibeTextFieldPreview() {
    VibeTextField(
        value = "",
        onValueChange = {},
        placeholder = "Phone number"
    )
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholderText: String = "",
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    placeholderStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    textColor: Color = Color.Unspecified,
    placeholderColor: Color = Color.Gray,
    backgroundColor: Color = Color.Transparent,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 0.dp,
    cornerRadius: Dp = 0.dp,
    keyboardType: KeyboardType = KeyboardType.Text,
    singleLine: Boolean = true,
    paddingValues: Dp = 16.dp,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
    cursorColor: Color = textColor
) {
    val shape = RoundedCornerShape(cornerRadius)

    // Dynamic modifier applying border, clipping, background, and padding
    val containerModifier = modifier
        .then(
            if (borderWidth > 0.dp && borderColor != Color.Transparent) {
                Modifier.border(borderWidth, borderColor, shape)
            } else Modifier
        )
        .clip(shape)
        .background(backgroundColor)
        .padding(paddingValues)

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = containerModifier,
        textStyle = textStyle.copy(color = textColor),
        cursorBrush = SolidColor(cursorColor),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = singleLine,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Leading Icon
                if (leadingIcon != null) {
                    Icon(
                        painter = painterResource(id = leadingIcon),
                        contentDescription = null,
                        tint = placeholderColor
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }

                // Input & Placeholder Canvas
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholderText,
                            style = placeholderStyle,
                            color = placeholderColor
                        )
                    }
                    innerTextField()
                }

                // Trailing Icon
                if (trailingIcon != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = trailingIcon),
                        contentDescription = null,
                        tint = placeholderColor
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF0F0F0)
@Composable
private fun CustomTextFieldPreview() {
    var plainText by remember { mutableStateOf("") }
    var filledText by remember { mutableStateOf("9876543210") }
    var borderedText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // 1. Plain Input (No background, No border - Ideal for your Phone Input)
        Text("1. Plain / Borderless Style", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            value = plainText,
            onValueChange = { plainText = it },
            placeholderText = "Enter Phone Number",
            keyboardType = KeyboardType.Number,
            textColor = Color(0xFF1E3C72),
            placeholderColor = Color(0xFF6F83C1),
            paddingValues = 8.dp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 2. Filled Style with Rounded Corners
        Text("2. Filled Card Style", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            value = filledText,
            onValueChange = { filledText = it },
            placeholderText = "Phone Number",
            backgroundColor = Color.White,
            cornerRadius = 12.dp,
            textColor = Color(0xFF1E3C72),
            placeholderColor = Color.Gray,
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 3. Outlined Style with Border
        Text("3. Outlined Style with Border", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            value = borderedText,
            onValueChange = { borderedText = it },
            placeholderText = "Enter your email",
            backgroundColor = Color.White,
            borderColor = Color(0xFF1E3C72),
            borderWidth = 1.5.dp,
            cornerRadius = 16.dp,
            textColor = Color.Black,
            placeholderColor = Color.LightGray,
            keyboardType = KeyboardType.Email
        )
    }
}