package com.manas.vibe.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manas.vibe.feature.auth.login.domain.model.Country

@Composable
fun PhoneNumberField(
    selectedCountry: Country,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    onCountryChange: (Country) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        CountryCodePicker(
            selectedCountry = selectedCountry,
            onCountrySelected = onCountryChange
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
private fun PhoneNumberFieldPreview() {
    PhoneNumberField(
        selectedCountry = Country(
            "","","",""
        ),
        phoneNumber = "6372298455",
        onPhoneNumberChange = {},
        onCountryChange = {}
    )
}