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

class AuthenticationManagerImpl(private val context: Context,
    private val httpClient: AuthenticationClient) : AuthenticationManager {

    override suspend fun getGoogleIdToken(): String? {
        val credentialManager = CredentialManager.create(context)

        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(context.getString(R.string.google_client))
            .setFilterByAuthorizedAccounts(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        return try {
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
                googleIdTokenCredential.idToken
            } else {
                null
            }
        } catch (e: Exception) {
            println(e)
            null
        }
    }

}