package com.manas.vibe.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PhoneNumberField(
    countryCode:String,
    phoneNumber: String,
    onPhoneNumberChange:(String)-> Unit,
    modifier: Modifier= Modifier
){
    Row(
        modifier=modifier
    ) {
        CountryCodePicker(
            countryCode = countryCode
        )
        VibeTextField(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            placeholder = "Phone number",
            keyboardType = KeyboardType.Phone,
            modifier = Modifier.weight(1f)
        )

    }
}
@Preview(showBackground = true)
@Composable
private fun PhoneNumberField(){
    PhoneNumberField(
        countryCode = "+91",
        phoneNumber = "6372298455",
        onPhoneNumberChange = {},

    )
}