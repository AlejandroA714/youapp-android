package sv.com.youapp.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sv.com.youapp.core.navigation.Routes
import sv.com.youapp.core.ui.common.GradientButton
import sv.com.youapp.core.ui.common.MainScaffold
import sv.com.youapp.feature.home.Home

@Composable
fun AppNavHost() {
    val navController: NavHostController = rememberNavController()
    MainScaffold(navController) {
        NavHost(navController, startDestination = "home") {
            composable(Routes.Home.route) {
                Home()
            }
            composable(Routes.Profile.route) {
                GradientButton("Hola Mundo") { }
            }
        }
    }
}