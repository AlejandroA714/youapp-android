package sv.com.youapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import sv.com.youapp.core.ui.navigation.AppNavHost
import sv.com.youapp.core.ui.navigation.AuthNavHost
import sv.com.youapp.core.ui.theme.YouAppTheme
import sv.com.youapp.core.vwm.AppViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        // Splash screen on Android < 12
        if (Build.VERSION.SDK_INT < 31) {
            setTheme(R.style.Theme_YouApp)
        }
        super.onCreate(savedInstanceState)
        setContent {
            val isLoggedIn by appViewModel.isLoggedIn.collectAsStateWithLifecycle()
            YouAppTheme {
                if (!isLoggedIn) {
                    AuthNavHost()
                } else {
                    AppNavHost()
                }
            }
        }
        appViewModel.handleAuthDeepLink(intent, getString(R.string.base_uri))
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        appViewModel.handleAuthDeepLink(intent, getString(R.string.base_uri))
    }
}
