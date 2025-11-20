package sv.com.youapp.feature.login.data

data class LoginUiState(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val isLoggedIn: Boolean = false
)