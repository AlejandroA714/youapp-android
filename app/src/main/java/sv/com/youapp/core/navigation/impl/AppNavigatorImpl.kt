package sv.com.youapp.core.navigation.impl

import androidx.navigation.NavHostController
import sv.com.youapp.core.navigation.AppNavigator
import sv.com.youapp.core.navigation.Route

class AppNavigatorImpl(private val navHost: NavHostController) : AppNavigator {

    override fun navigateTo(route: Route) {
        navHost.navigate(route.route)
    }
}