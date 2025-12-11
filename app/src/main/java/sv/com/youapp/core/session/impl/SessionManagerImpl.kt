package sv.com.youapp.core.session.impl

import android.content.Context
import androidx.core.content.edit
import sv.com.youapp.core.session.SessionManager

private const val SID = "sid"
private const val SESSION = "session"

class SessionManagerImpl(context: Context) : SessionManager {
    private val prefs = context.getSharedPreferences(SESSION, Context.MODE_PRIVATE)

    override fun createSession(sid: String) {
        prefs.edit {
            putString(SID, sid)
        }
    }

    override fun getSession(): String? {
        return prefs.getString(SID, null)
    }

    override fun revokeSession() {
        prefs.edit {
            remove(SID)
        }
    }
}