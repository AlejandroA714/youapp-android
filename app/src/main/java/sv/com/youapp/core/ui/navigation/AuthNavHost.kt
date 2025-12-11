package sv.com.youapp.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sv.com.youapp.core.navigation.LocalAppNavigator
import sv.com.youapp.core.navigation.Login
import sv.com.youapp.core.navigation.Recover
import sv.com.youapp.core.navigation.Register
import sv.com.youapp.core.navigation.impl.AppNavigatorImpl
import sv.com.youapp.feature.login.LoginScreen
import sv.com.youapp.feature.login.RecoverScreen
import sv.com.youapp.feature.login.RegisterScreen

@Composable
fun AuthNavHost() {
    val navController = rememberNavController()
    val appNavigator = remember(navController) {
        AppNavigatorImpl(navController)
    }
    CompositionLocalProvider(LocalAppNavigator provides appNavigator) {
        NavHost(
            navController = navController, startDestination = Login.route
        ) {
            composable(Login.route) {
                LoginScreen()
            }
            composable(Register.route) {
                RegisterScreen()
            }
            composable(Recover.route) {
                RecoverScreen()
            }
        }
    }
}