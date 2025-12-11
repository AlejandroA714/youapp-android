package sv.com.youapp.core.authentication

interface AuthenticationManager {
    suspend fun getGoogleIdToken(): String?
}