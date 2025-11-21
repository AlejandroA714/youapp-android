package sv.com.youapp.core.session

interface SessionManager {

    fun createSession(sid: String)

    fun getSession(): String?

    fun revokeSession()
}