package com.sv.youapp.app.activities

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.core.content.edit
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sv.youapp.app.R
import com.sv.youapp.app.auth.AuthManager
import com.sv.youapp.app.ui.login.LoginScreen
import androidx.core.net.toUri
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.sv.youapp.app.ui.ColorDemoScreen
import com.sv.youapp.app.ui.MainScaffold
import com.sv.youapp.app.ui.home.Home

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
            val navController = rememberNavController()

            NavHost(navController, startDestination = "home") {
                composable("login") { LoginScreen{} }
                composable("home") { MainScaffold { ColorDemoScreen() } }
                composable("profile") { MainScaffold {  } }
                composable("settings") { MainScaffold {  } }
            }
        } // Content
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