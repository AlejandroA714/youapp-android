package sv.com.youapp.core.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sv.com.youapp.core.navigation.LocalAppNavigator
import sv.com.youapp.core.navigation.LocalCurrentRoute
import sv.com.youapp.core.navigation.appRoutes
import sv.com.youapp.core.ui.AppPreview
import sv.com.youapp.core.ui.appViewModel
import sv.com.youapp.feature.home.HomeScreen

@Composable
fun MainScaffold(content: @Composable () -> Unit) {
    val navHost = LocalAppNavigator.current
    val currentRoute = LocalCurrentRoute.current
    Scaffold(
        bottomBar = {
            BottomAppBar {
                NavigationBar {
                    appRoutes
                        .forEach { item ->
                            NavigationBarItem(
                                selected = currentRoute == item.route,
                                onClick = { navHost.navigateTo(item) },
                                icon = {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = "Home",
                                        modifier = Modifier.size(32.dp),
                                    )
                                },
                              colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                                indicatorColor = Color.Transparent
                            )
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
    AppPreview{
        MainScaffold { HomeScreen(appViewModel(LocalContext.current)) }
    }
}

