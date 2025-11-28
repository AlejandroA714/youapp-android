package sv.com.youapp.feature.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import sv.com.youapp.core.events.GlobalEvent
import sv.com.youapp.core.events.GlobalEventDispatcher
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val eventDispatcher: GlobalEventDispatcher
) : ViewModel() {
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    val events: SharedFlow<GlobalEvent> = eventDispatcher.events
    fun startLogin() {
        _loading.value = true
        eventDispatcher.emit(GlobalEvent.LoginStarted)
       // _events.tryEmit(LoginEvent.LoginStarted(""))
    }

    fun setLoading(isLoading: Boolean) {
        _loading.value = isLoading
    }



    fun cancelLogin() {
        _loading.value = false
        eventDispatcher.emit(GlobalEvent.LoginCancelled("User ABORTED"))
    }


}