package com.manas.vibe.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manas.vibe.R
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


@Composable
fun PhoneNumber(
    selectedCountry: Country,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    onCountryChange: (Country) -> Unit,
    modifier: Modifier
){
    Box(
        Modifier.wrapContentHeight()
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = colorResource(R.color._98AAEA),
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.FFFFFF)),
        contentAlignment = Alignment.Center
    ){
       Row(
           modifier = Modifier
               .height(IntrinsicSize.Min)
               .padding(16.dp)
           ,
           verticalAlignment = Alignment.CenterVertically,
       ) {
           Text(
               text = "+91",
               style = MaterialTheme.typography.bodyLarge.copy(
                   fontWeight = FontWeight.Bold
               ),
               color = colorResource(R.color._1E3C72),
               modifier= Modifier.padding(end = 8.dp)

           )
           VerticalDivider(
               modifier = Modifier
                   .fillMaxHeight(),
               color = colorResource(R.color._6F83C1_50p),
               thickness = 2.dp
           )
           Text(
               text = "",
               style = MaterialTheme.typography.bodyLarge.copy(
                   fontWeight = FontWeight.Bold
               ),
               color = colorResource(R.color._1E3C72)

           )
           CustomTextField(
               value = phoneNumber,
               onValueChange = onPhoneNumberChange,
               modifier= Modifier
                   .weight(1f),
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
private fun PhoneNumber2(){
    PhoneNumber(
        selectedCountry = Country(
            "","","",""
        ),
        phoneNumber = "6372298455",
        onPhoneNumberChange = {},
        onCountryChange = {},
        modifier = Modifier
    )
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