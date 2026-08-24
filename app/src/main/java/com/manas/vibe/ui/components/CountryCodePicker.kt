package com.manas.vibe.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manas.vibe.feature.auth.data.countries
import com.manas.vibe.feature.auth.domain.model.Country

@Composable
fun CountryCodePicker(
    selectedCountry: Country,
    onCountrySelected: (Country) -> Unit,
    modifier: Modifier = Modifier
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
    ) {

        OutlinedCard(
            onClick = {
                expanded = true
            },
            shape = RoundedCornerShape(12.dp)
        ) {

            Text(
                text = "${selectedCountry.flag} ${selectedCountry.dialCode}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(
                    horizontal = 12.dp,
                    vertical = 16.dp
                )
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {

            countries.forEach { country ->

                DropdownMenuItem(
                    text = {
                        Text(
                            text = "${country.flag} ${country.name} ${country.dialCode}"
                        )
                    },
                    onClick = {

                        onCountrySelected(country)

                        expanded = false
                    }
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun CountryCodePreview() {
    CountryCodePicker(
        selectedCountry = Country(
            "","","",""
        ),
        onCountrySelected = {},

    )
}