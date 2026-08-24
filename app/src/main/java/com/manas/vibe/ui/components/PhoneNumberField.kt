package com.manas.vibe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manas.vibe.R
import com.manas.vibe.feature.auth.data.countries
import com.manas.vibe.feature.auth.domain.model.Country
@Composable
fun PhoneNumberField(
    selectedCountry: Country,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    onCountryChange: (Country) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = colorResource(R.color._98AAEA),
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.FFFFFF))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Country Code Picker Area
            Box {
                Row(
                    modifier = Modifier
                        .clickable { expanded = true }
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "${selectedCountry.flag} ${selectedCountry.dialCode}",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = colorResource(R.color._1E3C72)
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    countries.forEach { country ->
                        DropdownMenuItem(
                            text = {
                                Text(text = "${country.flag} ${country.name} (${country.dialCode})")
                            },
                            onClick = {
                                onCountryChange(country)
                                expanded = false
                            }
                        )
                    }
                }
            }

            VerticalDivider(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .height(24.dp),
                color = colorResource(R.color._6F83C1_50p),
                thickness = 2.dp
            )

            CustomTextField(
                value = phoneNumber,
                onValueChange = onPhoneNumberChange,
                modifier = Modifier.weight(1f),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                textColor = colorResource(R.color._1E3C72),
                placeholderText = "Phone Number",
                placeholderStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                placeholderColor = colorResource(R.color._6F83C1_50p),
                keyboardType = KeyboardType.Phone,
                singleLine = true,
                cursorColor = colorResource(R.color._1E3C72),
                paddingValues = 8.dp
            )
        }
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