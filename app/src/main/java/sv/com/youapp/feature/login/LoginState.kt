package sv.com.youapp.feature.login

data class LoginState(
    val loggingInProgress: Boolean,
    val loginKind: LoginKind,
)