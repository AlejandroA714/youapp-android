package sv.com.youapp.core.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(val route: String,
    icon: ImageVector) {
    data object Login : Routes("login", Icons.Outlined.LocationOn)
    data object Home : Routes("home", Icons.Outlined.Home)
    data object Profile : Routes("profile", Icons.Outlined.AccountBox)

  companion object {
    val bottomBarItems: List<Routes> by lazy {
      Routes::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
    }
  }
}