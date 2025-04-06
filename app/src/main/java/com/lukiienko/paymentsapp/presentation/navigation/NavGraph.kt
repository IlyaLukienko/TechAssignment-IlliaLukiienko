package com.lukiienko.paymentsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lukiienko.paymentsapp.presentation.screen.pinpad.PinPadScreen

object Routes {
    const val PIN_PAD = "pinpad"
    const val RECEIPT = "receipt"
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.PIN_PAD
    ) {
        composable(Routes.PIN_PAD) {
            PinPadScreen(navController = navController)
        }
        composable(Routes.RECEIPT) {

        }
    }
}