package sv.com.youapp.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(
    val route: String,
    val icon: ImageVector?
) {
    data object Home : Routes("home", Icons.Outlined.Home)
    data object Profile : Routes("profile", Icons.Outlined.AccountBox)

    data object Register : Routes("register", null)

    data object Login : Routes("login", null)
    companion object {
        val appRoutes: List<Routes> = listOf(
            Home,
            Profile
        )
    }
}