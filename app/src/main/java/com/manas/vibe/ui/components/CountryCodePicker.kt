package com.manas.vibe.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CountryCodePicker(
    countryCode:String,
    modifier: Modifier= Modifier
){
    Text(
        text = countryCode,
        modifier = modifier
    )

}

@Preview(showBackground = true)
@Composable
private fun CountryCodePreview(){
    CountryCodePicker(
        countryCode = "+91",
    )
}