package sv.com.youapp.core.navigation

import androidx.compose.runtime.staticCompositionLocalOf

interface AppNavigator {
    fun navigateTo(route: Route)
}

val LocalAppNavigator = staticCompositionLocalOf<AppNavigator> {
    error("No AppNavigator provided")
}

val LocalCurrentRoute = staticCompositionLocalOf<String?>{
    null
}