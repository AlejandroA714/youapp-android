package sv.com.youapp.feature.login

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sv.com.youapp.R
import sv.com.youapp.core.authentication.AuthenticationManager
import sv.com.youapp.core.session.SessionManager
import sv.com.youapp.core.ui.toast.ToastService
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val toastService: ToastService,
    private val sessionManager: SessionManager,
    private val authManager: AuthenticationManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginState(false, LoginKind.NONE))

    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    private fun setLoading(isLoading: Boolean, loginKind: LoginKind) {
        _uiState.value = _uiState.value.copy(loggingInProgress = isLoading, loginKind = loginKind)
    }
    fun startLogin() {
        setLoading(true, LoginKind.NATIVE)
        sessionManager.revokeSession()
    }
    fun cancelLogin() {
        if(_uiState.value.loggingInProgress){
            setLoading(false, LoginKind.NONE)
            val hasLogin = sessionManager.getSession() == null
            if(hasLogin){
                toastService.show(R.string.login_stop, Toast.LENGTH_SHORT)
            }
        }
    }
    fun startGoogle() {
        setLoading(true, LoginKind.GOOGLE)
        viewModelScope.launch {
            val dd = authManager.getGoogleIdToken()
            cancelLogin()
        }
    }
}