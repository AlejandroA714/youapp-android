package sv.com.youapp.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import sv.com.youapp.core.navigation.Home
import sv.com.youapp.core.navigation.LocalAppNavigator
import sv.com.youapp.core.navigation.LocalCurrentRoute
import sv.com.youapp.core.navigation.Profile
import sv.com.youapp.core.navigation.impl.AppNavigatorImpl
import sv.com.youapp.core.ui.common.GradientButton
import sv.com.youapp.core.ui.common.MainScaffold
import sv.com.youapp.feature.home.HomeScreen

@Composable
fun AppNavHost() {
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val appNavigator = remember(navController) {
        AppNavigatorImpl(navController)
    }
    CompositionLocalProvider(LocalAppNavigator provides appNavigator,
        LocalCurrentRoute provides currentRoute
    ) {
        MainScaffold {
            NavHost(navController, startDestination = "home") {
                composable(Home.route) {
                    HomeScreen()
                }
                composable(Profile.route) {
                    GradientButton("Hola Mundo") { }
                }
            }
        }
    }
}