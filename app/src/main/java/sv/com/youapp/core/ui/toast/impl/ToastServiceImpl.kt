package sv.com.youapp.core.ui.toast.impl

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import jakarta.inject.Inject
import sv.com.youapp.core.ui.toast.ToastService

class ToastServiceImpl @Inject constructor(private val context: Context): ToastService {

    override fun show(@StringRes msgId: Int, duration: Int) {
        Toast.makeText(context,context.getString(msgId), duration).show()
    }

    override fun show(msg: String, duration: Int) {
        Toast.makeText(context,msg, duration).show()
    }
}