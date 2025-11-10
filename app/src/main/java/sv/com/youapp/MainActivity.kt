package sv.com.youapp

import android.app.ComponentCaller
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.edit
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import sv.com.youapp.auth.AuthManager
import sv.com.youapp.core.ui.navigation.AppNavHost

class MainActivity: ComponentActivity() {
    //private lateinit var authManager: AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        // Splash screen on Android < 12
        if (Build.VERSION.SDK_INT < 31) {
            setTheme(R.style.Theme_YouApp)
        }
        super.onCreate(savedInstanceState)
        handleAuthDeepLink(intent)
        //authManager = AuthManager(this)

        setContent {
            AppNavHost()
        } // Content
    }

    override fun onNewIntent(intent: Intent, caller: ComponentCaller) {
        super.onNewIntent(intent)
        handleAuthDeepLink(intent)
    }


    // HANDLES CALLBACK
    private fun handleAuthDeepLink(intent: Intent?) {
        val data = intent?.data
        if (data != null && data.scheme == "youapp" && data.host == "oauth2") {
            val sid = data.getQueryParameter("sid")
            if (sid != null) {
                getSharedPreferences("auth", MODE_PRIVATE)
                    .edit {
                        putString("sid", sid)
                    }
            }
        }
    }

}