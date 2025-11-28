package sv.com.youapp.feature.home

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sv.com.youapp.core.ui.activityViewModel
import sv.com.youapp.core.ui.common.GradientButton
import sv.com.youapp.core.vwm.AppViewModel

@Composable
fun Home(viewModel: AppViewModel = activityViewModel()) {
    val scheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .background(scheme.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ColorBox("Primary", scheme.primary, scheme.onPrimary)
        ColorBox("Secondary", scheme.secondary, scheme.onSecondary)
        ColorBox("Tertiary", scheme.tertiary, scheme.onTertiary)
        ColorBox("Background", scheme.background, scheme.onBackground)
        ColorBox("Surface", scheme.surface, scheme.onSurface)
        ColorBox("Error", scheme.error, scheme.onError)
        GradientButton("Click ME!") {
            viewModel.viewModelScope.launch {
                viewModel.logout()
            }

        }
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