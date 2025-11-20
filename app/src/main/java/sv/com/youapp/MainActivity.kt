package sv.com.youapp

import android.app.ComponentCaller
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.content.edit
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import sv.com.youapp.core.ui.navigation.AppNavHost
import sv.com.youapp.core.ui.theme.YouAppTheme
import sv.com.youapp.feature.login.LoginScreen
import sv.com.youapp.feature.login.LoginViewModel
import sv.com.youapp.feature.login.data.LoginEvent
import sv.com.youapp.feature.login.doSomehting
import sv.com.youapp.feature.login.openInBrowser

class MainActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        // Splash screen on Android < 12
        if (Build.VERSION.SDK_INT < 31) {
            setTheme(R.style.Theme_YouApp)
        }
        super.onCreate(savedInstanceState)
        setContent {
            YouAppTheme {
                if(!viewModel.uiState.isLoggedIn){
                    LoginScreen(viewModel)
                }else{
                    AppNavHost()
                }
            }
        }
        handleAuthDeepLink(intent)
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.uiState.loading){
            viewModel.cancelLogin()
        }
    }

    override fun onNewIntent(intent: Intent, caller: ComponentCaller) {
        super.onNewIntent(intent)
        handleAuthDeepLink(intent)
    }

    private fun handleAuthDeepLink(intent: Intent?) {
        val data = intent?.data ?: return
        val isAppLink =
            data.scheme == "https" &&
                    data.host == getString(R.string.base_uri) &&
                    data.path?.startsWith("/oauth2/redirect") == true
        val isFallback =
            data.scheme == "youapp" &&
                    data.host == "oauth2"
        if (isFallback || isAppLink) {
            val sid = data.getQueryParameter("sid")
            //TODO: DO REAL SESSION MANAGEMENT
            if (sid != null) {
                viewModel.completeLogin(sid)
                getSharedPreferences("auth", MODE_PRIVATE)
                    .edit {
                        putString("sid", sid)
                    }
            }
        }
    }

}