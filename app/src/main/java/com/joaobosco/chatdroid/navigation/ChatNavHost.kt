package com.joaobosco.chatdroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.joaobosco.chatdroid.ui.feature.signin.SignInRoute
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
            SplashRoute(
                onNavigateToSignIn = {
                    navController.navigate(
                        route = SignInRoute,
                        navOptions = navOptions {
                            popUpTo(SplashRoute) {
                                inclusive = true
                            }
                        }
                    )
                }
            )
        }
        composable<SignInRoute> {
            SignInRoute()
        }
        composable<SignUpRoute> {

        }
    }
}