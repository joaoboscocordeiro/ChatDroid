package com.joaobosco.chatdroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joaobosco.chatdroid.ui.feature.splash.SplashRoute
import kotlinx.serialization.Serializable

/**
 * Created by "João Bosco" on 25/07/2024.
 */

@Serializable
object SplashRoute

@Serializable
object SignInRoute

@Serializable
object SignUpRoute

@Composable
fun ChatNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = SplashRoute) {
        composable<SplashRoute> {
            SplashRoute()
        }
        composable<SignInRoute> {

        }
        composable<SignUpRoute> {

        }
    }
}