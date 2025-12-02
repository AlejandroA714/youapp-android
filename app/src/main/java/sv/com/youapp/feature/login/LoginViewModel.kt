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
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    fun startLogin() {
        setLoading(true)
        sessionManager.revokeSession()
    }

    fun cancelLogin() {
        if(_loading.value){
            setLoading(false)
            val hasLogin = sessionManager.getSession() == null
            if(hasLogin){
                toastService.show(R.string.login_stop, Toast.LENGTH_SHORT)
            }
        }
    }

    fun setLoading(isLoading: Boolean) {
        _loading.value = isLoading
    }
}