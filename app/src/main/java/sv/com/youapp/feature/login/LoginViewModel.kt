package sv.com.youapp.feature.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel: ViewModel() {
  private val _isAuth = MutableStateFlow(false)
  val isAuth: StateFlow<Boolean> = _isAuth

  fun setAuth(value: Boolean) {
    _isAuth.value = value
  }

}