package sv.com.youapp.feature.login

import android.widget.Toast
import androidx.activity.ComponentActivity
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import sv.com.youapp.R
import sv.com.youapp.core.events.GlobalEvent
import sv.com.youapp.core.ui.common.GradientButton
import sv.com.youapp.core.ui.common.LoadingGradientButton
import sv.com.youapp.core.ui.openInBrowser

@Composable
fun LoginScreen(loginVM: LoginViewModel = hiltViewModel(),
                onRegisterClick: () -> Unit) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val loading by loginVM.loading.collectAsStateWithLifecycle()
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
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
        LoadingGradientButton(loading, stringResource(R.string.login)) {
            loginVM.startLogin()
        }
        Spacer(modifier = Modifier.height(8.dp))
        GradientButton(stringResource(R.string.siginup)) {
            loginVM.setLoading(true)
            onRegisterClick()
        }
        LaunchedEffect(Unit, lifecycleOwner) {
                loginVM.events.collect { event ->
                    when (event) {
                        is GlobalEvent.LoginCancelled -> Toast.makeText(context, event.reason,Toast.LENGTH_SHORT).show()
                        GlobalEvent.LoginStarted -> {
                            openInBrowser(context)
                        }
                        //GlobalEvent.ActivityResumed -> loginVM.cancelLogin()
                        //GlobalEvent.LoginSuccess -> loginVM.cancelLogin()
                        else -> Unit
                    }
                }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewLoginScreen() {
    LoginScreen(){}
}