package com.sv.youapp.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MainScaffold(content: @Composable () -> Unit) {
    val appDarkColors = darkColorScheme(
        primary = Color.Yellow,
        secondary = Color.Green,
        background = Color(0xFF1A0938),
        surface = Color(0xFF1A0938),
        onPrimary = Color.White,
        onSecondary = Color.White,
        onBackground = Color.White,
        onSurface = Color.White,
        error = Color(0xFFB00020),
        onError = Color.White
    )
    MaterialTheme(colorScheme = appDarkColors){
        Scaffold(
            bottomBar = {
                BottomAppBar {
                    Text("Barra inferior")
                }
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                content()
            }
        }
    }
}

@Composable
fun ColorDemoScreen() {
    val scheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ColorBox("${scheme.background}", scheme.primary, scheme.onPrimary)
        ColorBox("Secondary", scheme.secondary, scheme.onSecondary)
        ColorBox("Tertiary", scheme.tertiary, scheme.onTertiary)
        ColorBox("Background", scheme.background, scheme.onBackground)
        ColorBox("Surface", scheme.surface, scheme.onSurface)
        ColorBox("Error", scheme.error, scheme.onError)
    }
}

@Composable
fun ColorBox(label: String, bg: Color, fg: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(bg),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = fg,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}