package com.sv.youapp.app.auth

import android.content.Context
import com.sv.youapp.app.R
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import android.util.Log
import androidx.core.net.toUri
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.ResponseTypeValues

class AuthManager(private val context: Context) {
    private val authService by lazy { AuthorizationService(context) }

    fun startAuthFlow(onAuthIntentReady: (intent: android.content.Intent) -> Unit) {
        val discoveryUri = context.getString(R.string.auth_discovery_uri).toUri()
        AuthorizationServiceConfiguration.fetchFromUrl(discoveryUri) { serviceConfig, ex ->
            if (ex != null) {
                Log.e("Auth", "Error discovery: ${ex.errorDescription}")
                return@fetchFromUrl
            }
            if (serviceConfig != null) {
                val authRequest = AuthorizationRequest.Builder(
                    serviceConfig,
                    context.getString(R.string.auth_client_id),
                    ResponseTypeValues.CODE,
                    context.getString(R.string.auth_redirect_uri).toUri()
                )
                    .setScope(context.getString(R.string.auth_scope))
                    .build()

                val intent = authService.getAuthorizationRequestIntent(authRequest)
                onAuthIntentReady(intent)
            }
        }
    }

    fun handleAuthResponse(
        intent: android.content.Intent,
        onTokensReceived: (String?, String?) -> Unit
    ) {
        val resp = AuthorizationResponse.fromIntent(intent)
        val ex = AuthorizationException.fromIntent(intent)

        if (resp != null) {
            val tokenRequest = resp.createTokenExchangeRequest()
            authService.performTokenRequest(tokenRequest) { response, ex2 ->
                if (response != null) {
                    onTokensReceived(response.accessToken, response.refreshToken)
                } else {
                    Log.e("Auth", "Token exchange error: ${ex2?.errorDescription}")
                }
            }
        } else {
            Log.e("Auth", "Auth error: ${ex?.errorDescription}")
        }
    }



}