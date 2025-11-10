package sv.com.youapp.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import sv.com.youapp.core.ui.common.MainScaffold
import sv.com.youapp.core.ui.theme.YouAppTheme
import sv.com.youapp.feature.home.Home
import sv.com.youapp.feature.login.LoginScreen

@Composable
fun AppNavHost() {
  val navController = rememberNavController()
  val backStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = backStackEntry?.destination?.route
  YouAppTheme {
    MainScaffold(currentRoute) {
      NavHost(navController, startDestination = "home") {
          composable(Routes.Login.route){
            LoginScreen()
          }
          composable(Routes.Home.route){
            Home()
          }
      }
    }
  }

}