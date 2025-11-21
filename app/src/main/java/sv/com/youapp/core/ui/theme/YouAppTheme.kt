package sv.com.youapp.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import sv.com.youapp.R


@Composable
fun YouAppTheme(content: @Composable () -> Unit) {
    val isDark: Boolean = isSystemInDarkTheme()
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
            surfaceContainer = colorResource(R.color.surfaceContainer),
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
            surfaceContainer = colorResource(R.color.surfaceContainer),
            error = colorResource(R.color.error),
            onError = colorResource(R.color.colorOnError)
        )
    }
    MaterialTheme(
        colorScheme = scheme,
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}
