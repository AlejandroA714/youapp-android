package sv.com.youapp.feature.login

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
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import sv.com.youapp.R
import sv.com.youapp.core.session.impl.SessionManagerImpl
import sv.com.youapp.core.ui.common.GradientButton
import sv.com.youapp.core.ui.common.LoadingGradientButton
import sv.com.youapp.core.ui.openInBrowser
import sv.com.youapp.core.ui.toast.impl.ToastServiceImpl

@Composable
fun LoginScreen(
    loginVM: LoginViewModel = hiltViewModel(),
    onRegisterClick: () -> Unit
) {
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
            openInBrowser(context)
        }
        Spacer(modifier = Modifier.height(16.dp))
        GradientButton(stringResource(R.string.siginup)) {
            onRegisterClick()
        }
        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    loginVM.cancelLogin()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewLoginScreen() {
    val vm = LoginViewModel(ToastServiceImpl(LocalContext.current),
        SessionManagerImpl(LocalContext.current)
    )
    LoginScreen(vm) {}
}