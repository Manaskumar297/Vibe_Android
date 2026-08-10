package com.manas.vibe.app.navigation

sealed class Destination(val route: String){
    data object Home: Destination("home")
    data object Splash: Destination("splash")
    data object Login: Destination("login")
    data object OtpScreen: Destination("otpScreen")
}