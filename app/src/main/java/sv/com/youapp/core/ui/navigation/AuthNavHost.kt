package sv.com.youapp.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sv.com.youapp.core.navigation.Routes
import sv.com.youapp.feature.login.LoginScreen
import sv.com.youapp.feature.login.RegisterScreen

@Composable
fun AuthNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable(Routes.Login.route) {
            LoginScreen(
                onRegisterClick = { navController.navigate("register") }
            )
        }
        composable(Routes.Register.route) {
            RegisterScreen()
        }
    }
}