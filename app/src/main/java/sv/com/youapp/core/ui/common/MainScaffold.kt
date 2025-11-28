package sv.com.youapp.core.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import sv.com.youapp.core.navigation.Routes
import sv.com.youapp.core.ui.theme.YouAppTheme
import sv.com.youapp.feature.home.Home

@Composable
fun MainScaffold(navHost: NavHostController, content: @Composable () -> Unit) {
    val navBackStackEntry by navHost.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            BottomAppBar {
                NavigationBar {
                    Routes.appRoutes
                        .forEach { item ->
                            NavigationBarItem(
                                selected = currentRoute == item.route,
                                onClick = { navHost.navigate(item.route) },
                                icon = {
                                    Icon(
                                        imageVector = item.icon!!,
                                        contentDescription = "Home",
                                        modifier = Modifier.size(32.dp),
                                    )
                                },
//                              colors = NavigationBarItemDefaults.colors(
//                                selectedIconColor = Color.White,
//                                unselectedIconColor = Color.White.copy(alpha = 0.6f),
//                                indicatorColor = Color.Transparent
//                            )
                            )
                        }
                }
            }
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            content()
        }
    }
}

@Composable
@Preview(showBackground = false, showSystemUi = true)
fun PreviewMainScaffold() {
    val navHost = rememberNavController()
    YouAppTheme { MainScaffold(navHost) { Home() } }
}

