package sv.com.youapp.core.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import sv.com.youapp.R

@Composable
inline fun <reified VM : ViewModel> activityViewModel(): VM {
    val activity = LocalContext.current as ComponentActivity
    return hiltViewModel(activity)
}

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