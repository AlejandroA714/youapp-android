package com.sv.youapp.app.activities

import android.app.ComponentCaller
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.material3.MaterialTheme
import androidx.core.content.edit
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sv.youapp.app.R
import com.sv.youapp.app.auth.AuthManager
import com.sv.youapp.app.ui.login.LoginScreen
import androidx.core.net.toUri

class MainActivity: ComponentActivity() {
    private lateinit var authManager: AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        // Splash screen on Android < 12
        if (Build.VERSION.SDK_INT < 31) {
            setTheme(R.style.Theme_YouApp)
        }
        super.onCreate(savedInstanceState)
        handleAuthDeepLink(intent)
        authManager = AuthManager(this)
        setContent {
            MaterialTheme {
                LoginScreen {
                    openInBrowser(this)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent, caller: ComponentCaller) {
        super.onNewIntent(intent)
        handleAuthDeepLink(intent)
    }

    fun openInBrowser(context: Context) {
       val uri: Uri = context.getString(R.string.base_uri).toUri().buildUpon()
           .appendPath("oauth2")
           .appendPath("login").build()
        val cct = CustomTabsIntent.Builder()
            .setShowTitle(true)
            .build()
        cct.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            cct.launchUrl(context, uri)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(
                Intent(Intent.ACTION_VIEW, uri).apply {
                    addCategory(Intent.CATEGORY_BROWSABLE)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
            )
        }
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