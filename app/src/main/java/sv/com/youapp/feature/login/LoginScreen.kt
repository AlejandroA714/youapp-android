package sv.com.youapp.feature.login

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import sv.com.youapp.R
import sv.com.youapp.core.ui.common.GradientButton
import sv.com.youapp.core.ui.common.LoadingGradientButton
import sv.com.youapp.feature.login.data.LoginEvent

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val uiState = viewModel.uiState
    val context = LocalContext.current
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
                ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(
            painterResource(R.drawable.ic_logo),
            contentDescription = "LOGO",
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            stringResource(R.string.welcome),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        LoadingGradientButton(uiState.value.loading, stringResource(R.string.login)) {
            viewModel.startLogin()
        }
        Spacer(modifier = Modifier.height(8.dp))
        GradientButton(stringResource(R.string.siginup)) {
            //TODO: REGISTRARSE
        }
        LaunchedEffect(Unit) {
            viewModel.events.collect { event ->
                when (event) {
                    is LoginEvent.LoginSuccess -> Unit
                    is LoginEvent.LoginStarted -> openInBrowser(context)
                    is LoginEvent.LoginCancelled -> Toast
                        .makeText(context, event.reason, Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
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

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewLoginScreen() {
    LoginScreen(viewModel())
}