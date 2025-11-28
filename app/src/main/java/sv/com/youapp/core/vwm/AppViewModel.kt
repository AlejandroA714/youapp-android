package sv.com.youapp.core.vwm

import android.content.Intent
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import sv.com.youapp.R
import sv.com.youapp.core.events.GlobalEvent
import sv.com.youapp.core.events.GlobalEventDispatcher
import sv.com.youapp.core.session.SessionManager
import sv.com.youapp.core.ui.toast.ToastEvent
import sv.com.youapp.core.ui.toast.ToastService

@HiltViewModel
class AppViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val eventDispatcher: GlobalEventDispatcher,
    private val toastService: ToastService
) : ViewModel() {
    private var _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()
    val events: SharedFlow<GlobalEvent> = eventDispatcher.events

    fun login(sid: String) {
        eventDispatcher.emit(GlobalEvent.LoginSuccess)
        sessionManager.createSession(sid)
        toastService.show(R.string.welcome,LENGTH_LONG)
        _isLoggedIn.value = true
    }

    fun onActivityResumed() {
        eventDispatcher.emit(GlobalEvent.ActivityResumed)
    }


    suspend fun logout() {
        eventDispatcher.forceEmit(GlobalEvent.Logout)
        toastService.show(R.string.logout,LENGTH_LONG)
        _isLoggedIn.value = false
    }

    fun handleAuthDeepLink(intent: Intent?) {
        val data = intent?.data ?: return
        val isAppLink =
            data.scheme == "https" &&
                    data.host == "wombed-intramuscular-lanell.ngrok-free.app" &&
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