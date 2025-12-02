package sv.com.youapp.core.vwm

import android.content.Intent
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import sv.com.youapp.R
import sv.com.youapp.core.session.SessionManager
import sv.com.youapp.core.ui.toast.ToastService

@HiltViewModel
class AppViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val toastService: ToastService
) : ViewModel() {
    private var _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    fun login(sid: String) {
        sessionManager.createSession(sid)
        toastService.show(R.string.welcome,LENGTH_LONG)
        _isLoggedIn.value = true
    }
    fun logout() {
        toastService.show(R.string.logout,LENGTH_LONG)
        _isLoggedIn.value = false
    }

    fun handleAuthDeepLink(intent: Intent?, host: String) {
        val data = intent?.data ?: return
        val isAppLink =
            data.scheme == "https" &&
                    data.host == host &&
                    data.path?.startsWith("/oauth2/redirect") == true
        val isFallback =
            data.scheme == "youapp" &&
                    data.host == "oauth2"
        if (isFallback || isAppLink) {
            val sid = data.getQueryParameter("sid")
            //TODO: DO REAL SESSION MANAGEMENT
            if (sid != null) {
               login(sid)
            }
        }
    }

}