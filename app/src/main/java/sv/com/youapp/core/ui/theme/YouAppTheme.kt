package sv.com.youapp.core.ui.theme

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import sv.com.youapp.R
import sv.com.youapp.core.ui.common.ColorBox


@Composable
fun YouAppTheme(content: @Composable () -> Unit) {
    val isDark: Boolean = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        true
    } else {
        isSystemInDarkTheme()
    }


    val scheme = if (isDark) {
        darkColorScheme(
            primary = colorResource(R.color.primary),
            onPrimary = colorResource(R.color.colorOnPrimary),
            secondary = colorResource(R.color.secondary),
            onSecondary = colorResource(R.color.colorOnSecondary),
            background = colorResource(R.color.background),
            onBackground = colorResource(R.color.colorOnBackground),
            surface = colorResource(R.color.surface),
            onSurface = colorResource(R.color.colorOnSurface),
            error = colorResource(R.color.error),
            onError = colorResource(R.color.colorOnError)
        )
    } else {
        lightColorScheme(
            primary = colorResource(R.color.primary),
            onPrimary = colorResource(R.color.colorOnPrimary),
            secondary = colorResource(R.color.secondary),
            onSecondary = colorResource(R.color.colorOnSecondary),
            background = colorResource(R.color.background),
            onBackground = colorResource(R.color.colorOnBackground),
            surface = colorResource(R.color.surface),
            onSurface = colorResource(R.color.colorOnSurface),
            error = colorResource(R.color.error),
            onError = colorResource(R.color.colorOnError)
        )
    }
    MaterialTheme(
        colorScheme = scheme,
    ) {
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
    }
}



}