package sv.com.youapp.feature.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import sv.com.youapp.feature.login.data.LoginEvent
import sv.com.youapp.feature.login.data.LoginUiState

class LoginViewModel: ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
       private set

    private val _events = MutableSharedFlow<LoginEvent>()
    val events: SharedFlow<LoginEvent> = _events.asSharedFlow()

    fun startLogin(){
        viewModelScope.launch {
            uiState = uiState.copy(loading = true)
            _events.emit(LoginEvent.LoginStarted(""))
        }
    }

    fun cancelLogin() {
        uiState = uiState.copy(loading = false)
        viewModelScope.launch {
            _events.emit(LoginEvent.LoginCancelled("User Aborted Operation"))
        }
    }

    fun completeLogin(sid: String) {
        uiState = uiState.copy(isLoggedIn = true, loading = false)
        viewModelScope.launch {
            _events.emit(LoginEvent.LoginSuccess(sid))
        }

    }

}