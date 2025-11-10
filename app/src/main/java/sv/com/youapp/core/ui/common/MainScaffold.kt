package sv.com.youapp.core.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import sv.com.youapp.R

@Composable
fun MainScaffold(currentRoute: String?,content: @Composable () -> Unit) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = colorResource(R.color.background)
            ) {
                NavigationBar(
                    containerColor = Color.Transparent
                ) {
                    listOf("home", "profile", "settings","account")
                        .forEach { item ->
                            NavigationBarItem(selected = currentRoute == item , onClick =  {} , icon = {Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Home",
                                modifier = Modifier.size(20.dp),
                            )}, colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                                indicatorColor = Color.Transparent
                            ) )
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

