package sv.com.youapp.feature.login

import android.widget.Toast
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import sv.com.youapp.R
import sv.com.youapp.core.session.SessionManager
import sv.com.youapp.core.ui.toast.ToastService
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val toastService: ToastService,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val _loggingInProgress = MutableStateFlow(false)
    val loggingInProgress: StateFlow<Boolean> = _loggingInProgress.asStateFlow()

    private val _loginKind = MutableStateFlow(LoginKind.NONE)
    val loginKind: StateFlow<LoginKind> = _loginKind.asStateFlow()
    fun startLogin() {
        setLoading(true)
        _loginKind.value = LoginKind.NATIVE
        sessionManager.revokeSession()
    }

    fun cancelLogin() {
        if(_loggingInProgress.value){
            setLoading(false)
            _loginKind.value = LoginKind.NONE
            val hasLogin = sessionManager.getSession() == null
            if(hasLogin){
                toastService.show(R.string.login_stop, Toast.LENGTH_SHORT)
            }
        }
    }

    fun setLoading(isLoading: Boolean) {
        _loggingInProgress.value = isLoading
    }

    fun startApple() {
        _loginKind.value = LoginKind.APPLE
        setLoading(true)
    }

    fun startGoogle() {
        _loginKind.value = LoginKind.GOOGLE
        setLoading(true)
    }

    fun star() {
        setLoading(true)
        _loginKind.value = LoginKind.DEBUG
    }
}