package com.devpaul.infoxperu.feature.user_start

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Support : Screen("support")
}

@Composable
fun StartNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
//        composable(Screen.Register.route) {
//            RegisterScreen(navController)
//        }
//        composable(Screen.Support.route) {
//            SupportScreen(navController)
//        }
    }
}
