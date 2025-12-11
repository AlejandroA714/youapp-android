package sv.com.youapp.core.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.core.net.toUri
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import sv.com.youapp.R
import sv.com.youapp.core.navigation.LocalAppNavigator
import sv.com.youapp.core.navigation.LocalCurrentRoute
import sv.com.youapp.core.navigation.impl.AppNavigatorImpl
import sv.com.youapp.core.session.SessionManager
import sv.com.youapp.core.session.impl.SessionManagerImpl
import sv.com.youapp.core.ui.theme.YouAppTheme
import sv.com.youapp.core.ui.toast.ToastService
import sv.com.youapp.core.ui.toast.impl.ToastServiceImpl
import sv.com.youapp.core.vwm.AppViewModel

@Composable
inline fun <reified VM : ViewModel> activityViewModel(): VM {
    val activity = LocalActivity.current as ComponentActivity
    return hiltViewModel(activity)
}

@Composable
fun AppPreview(content: @Composable () -> Unit) {
    val navController = rememberNavController()
    val appNavigator = remember(navController) {
        AppNavigatorImpl(navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    CompositionLocalProvider(
        LocalAppNavigator provides appNavigator,
        LocalCurrentRoute provides currentRoute
    ) {
        YouAppTheme {
            content()
        }
    }
}

fun appViewModel(context: Context): AppViewModel {
    val sessionManager: SessionManager = SessionManagerImpl(context)
    val toastService: ToastService = ToastServiceImpl(context)
    return AppViewModel(sessionManager, toastService)
}

//TODO: SUPPORT CUSTOM URL
fun openInBrowser(context: Context) {
    val uri: Uri = ("https://" + context.getString(R.string.base_uri)).toUri().buildUpon()
        .appendPath("oauth2")
        .appendPath("login")
        .build()
    val cct = CustomTabsIntent.Builder()
        .setShowTitle(true)
        .build()
    cct.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_TASK)
    try {
        cct.launchUrl(context, uri)
    } catch (_: ActivityNotFoundException) {
        context.startActivity(
            Intent(Intent.ACTION_VIEW, uri).apply {
                addCategory(Intent.CATEGORY_BROWSABLE)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        )
    }
}