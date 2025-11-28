package sv.com.youapp.core.ui.toast

import android.widget.Toast.LENGTH_LONG
import androidx.annotation.StringRes

interface ToastService {
    fun show(@StringRes msgId: Int, duration: Int = LENGTH_LONG)
    fun show(msg: String, duration: Int = LENGTH_LONG)

}