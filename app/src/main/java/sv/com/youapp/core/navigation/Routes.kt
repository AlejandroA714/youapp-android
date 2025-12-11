package sv.com.youapp.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
data object Home : DrawableRoute("home", Icons.Outlined.Home)
data object Profile : DrawableRoute("profile", Icons.Outlined.AccountBox)
data object Register : Route("register")
data object Recover : Route("recover")
data object Login : Route("login")

val appRoutes: List<DrawableRoute> = listOf(
    Home,
    Profile
)

sealed class Route(
    val route: String
)

sealed class DrawableRoute(
    route: String,
    val icon: ImageVector
) : Route(route)

