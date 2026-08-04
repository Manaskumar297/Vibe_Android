package com.manas.vibe.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manas.vibe.feature.auth.login.presentation.LoginScreen
import com.manas.vibe.feature.home.presentation.HomeScreen
import com.manas.vibe.feature.splash.presentation.SplashScreen

@Composable
fun AppNavigation(){
    val navController= rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.Splash.route
    ){
        composable(route = Destination.Splash.route){
            SplashScreen()
        }

        composable (Destination.Login.route){
            LoginScreen()
        }

        composable (
            route= Destination.Home.route
        ){
            HomeScreen()
        }
    }
}