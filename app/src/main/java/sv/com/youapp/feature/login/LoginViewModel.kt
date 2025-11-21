package sv.com.youapp.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import sv.com.youapp.core.session.SessionManager
import sv.com.youapp.feature.login.data.LoginEvent
import sv.com.youapp.feature.login.data.LoginUiState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()
    private val _events = MutableSharedFlow<LoginEvent>()
    val events: SharedFlow<LoginEvent> = _events.asSharedFlow()

    init {
        val sid:String? = sessionManager.getSession()
        sid?.let { setLoggedIn(it) }
    }
    fun startLogin() {
        setLoading(true)
        viewModelScope.launch {
            _events.emit(LoginEvent.LoginStarted(""))
        }
    }

    private fun setLoading(isLoading: Boolean) {
        _uiState.update { it.copy(loading = isLoading) }
    }

    private fun setLoggedIn(sid: String) {
        _uiState.update { it.copy(isLoggedIn = true, loading = false) }
    }

    fun cancelLogin() {
        setLoading(false)
        //uiState = uiState.copy(loading = false)
        viewModelScope.launch {
            _events.emit(LoginEvent.LoginCancelled("User Aborted Operation"))
        }
    }

    fun completeLogin(sid: String) {
        //uiState = uiState.copy(isLoggedIn = true, loading = false)
        viewModelScope.launch {
            _events.emit(LoginEvent.LoginSuccess(sid))
        }
    }

}