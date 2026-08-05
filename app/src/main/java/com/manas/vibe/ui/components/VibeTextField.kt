package com.manas.vibe.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun VibeTextField (
    value:String,
    onValueChange:(String)-> Unit,
    placeHolder: String,
    modifier: Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    enable: Boolean=true,
    singleLine: Boolean=true
){
    OutlinedTextField(
        value=value,
        onValueChange=onValueChange,
        placeholder = {
            Text(
                text = placeHolder
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType=keyboardType
        ),
        enabled = enable,
        singleLine = singleLine,
        modifier = modifier
    )
}