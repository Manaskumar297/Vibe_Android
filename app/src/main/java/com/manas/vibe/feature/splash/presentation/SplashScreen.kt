package com.manas.vibe.feature.splash.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.manas.vibe.R
import com.manas.vibe.ui.theme.Dimens

@Composable
fun SplashScreen(){

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = "Vibe Logo",
            modifier = Modifier
                .size(Dimens.splashLogoSize)
                .align(Alignment.Center)
        )
        Text(
            text = "Connect . Share . Vibe",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = Dimens.splashBottomPadding)




        )
    }
}