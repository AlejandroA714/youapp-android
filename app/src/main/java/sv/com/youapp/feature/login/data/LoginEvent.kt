package sv.com.youapp.feature.login.data

sealed class LoginEvent {
    data class LoginStarted(val uri: String) : LoginEvent()
    data class LoginSuccess(val sid: String): LoginEvent()
    data class LoginCancelled(val reason: String): LoginEvent()
}