package com.sv.youapp.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sv.youapp.app.auth.AuthManager
import com.sv.youapp.app.ui.login.LoginScreen

class MainActivity: ComponentActivity() {
    private lateinit var authManager: AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        // Splash screen on Android < 12
        if (android.os.Build.VERSION.SDK_INT < 31) {
            setTheme(R.style.Theme_YouApp)
        }
        super.onCreate(savedInstanceState)
        authManager = AuthManager(this)
        setContent{
            LoginScreen {
                authManager.startAuthFlow { intent: Intent ->
                    startActivityForResult(intent,1001)
                }
            }
        }
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      " +
            "which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n     " +
            " contracts for common intents available in\n     " +
            " {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n   " +
            "   testing, and allow receiving results in separate, testable classes independent from your\n     " +
            " activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n     " +
            " with the appropriate {@link ActivityResultContract} and handling the result in the\n     " +
            " {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001 && data != null) {
            authManager.handleAuthResponse(data) { accessToken, refreshToken ->
                Log.d("Auth", "Access token: $accessToken")
                Log.d("Auth", "Refresh token: $refreshToken")
                // Aquí deberías guardar los tokens en EncryptedSharedPreferences
            }
        }
    }

}