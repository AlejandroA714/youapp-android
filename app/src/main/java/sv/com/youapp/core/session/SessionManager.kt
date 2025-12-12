package sv.com.youapp.core.session

import kotlinx.coroutines.flow.Flow

interface SessionManager {

    val isLoggedIn: Flow<Boolean>
    fun createSession(sid: String)

    fun getSession(): String?

    fun revokeSession()
}