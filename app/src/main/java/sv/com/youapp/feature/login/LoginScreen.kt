package sv.com.youapp.feature.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
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
import sv.com.youapp.core.authentication.impl.AuthenticationManagerImpl
import sv.com.youapp.core.navigation.LocalAppNavigator
import sv.com.youapp.core.navigation.Recover
import sv.com.youapp.core.navigation.Register
import sv.com.youapp.core.network.MockAuthenticationClient
import sv.com.youapp.core.session.impl.SessionManagerImpl
import sv.com.youapp.core.ui.AppPreview
import sv.com.youapp.core.ui.common.AppleButton
import sv.com.youapp.core.ui.common.GoogleButton
import sv.com.youapp.core.ui.common.GradientButton
import sv.com.youapp.core.ui.common.LoadingGradientButton
import sv.com.youapp.core.ui.openInBrowser
import sv.com.youapp.core.ui.toast.impl.ToastServiceImpl

@SuppressLint("LocalContextGetResourceValueCall")
@Composable
fun LoginScreen(
    loginVM: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val navigator = LocalAppNavigator.current
    val uiState: LoginState by loginVM.uiState.collectAsStateWithLifecycle()
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
        LoadingGradientButton(
            uiState.loginKind == LoginKind.NATIVE,
            !uiState.loggingInProgress,
            stringResource(R.string.login)
        ) {
            loginVM.startLogin()
            openInBrowser(context)
        }
        Spacer(modifier = Modifier.height(16.dp))
        GradientButton(stringResource(R.string.siginup)) {
            navigator.navigateTo(Register)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            GoogleButton(uiState.loginKind == LoginKind.GOOGLE, !uiState.loggingInProgress) {
                loginVM.startGoogle()
            }
            AppleButton(uiState.loginKind == LoginKind.APPLE, enabled = false) {}
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.recover_pass),
            modifier = Modifier.clickable {
                navigator.navigateTo(Recover)
            },
            color = colorResource(R.color.purple_text),
            style = MaterialTheme.typography.bodyMedium
        )

        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                //TODO: IF GOOGLE SHOW TOAST
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
@SuppressLint("ViewModelConstructorInComposable")
fun PreviewLoginScreen() {
    val vm = LoginViewModel(
        ToastServiceImpl(LocalContext.current),
        SessionManagerImpl(LocalContext.current),
        AuthenticationManagerImpl(
            LocalContext.current, MockAuthenticationClient(),
            ToastServiceImpl(LocalContext.current)
        )
    )
    AppPreview {
        LoginScreen(vm)
    }

}