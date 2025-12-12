package sv.com.youapp.core.authentication.impl

import android.content.Context
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import sv.com.youapp.R
import sv.com.youapp.core.authentication.AuthenticationManager
import sv.com.youapp.core.network.AuthenticationClient
import sv.com.youapp.core.ui.toast.ToastService

class AuthenticationManagerImpl(
    private val context: Context,
    private val httpClient: AuthenticationClient,
    private val toast: ToastService
) : AuthenticationManager {

    override suspend fun getGoogleIdToken(): String? {
        val credentialManager = CredentialManager.create(context)

        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(context.getString(R.string.google_client))
            .setFilterByAuthorizedAccounts(false)
            .build()
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
        try {
            val result = credentialManager.getCredential(
                context = context,
                request = request
            )
            val credential: Credential = result.credential
            if (credential is CustomCredential &&
                credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
            ) {
                val googleIdTokenCredential =
                    GoogleIdTokenCredential.createFrom(credential.data)
                //TODO: CALL BACKEND
                val dd = httpClient.google(googleIdTokenCredential.idToken)
                return dd.sid
            } else {
                toast.show(R.string.google_invalid_credential)
            }
        } catch (e: Exception) {
            println(e)
            toast.show(R.string.google_failed)
        }
        return null
    }

}