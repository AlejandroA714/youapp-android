package sv.com.youapp.core.network

import sv.com.youapp.feature.login.GoogleResponse

class MockAuthenticationClient: AuthenticationClient {
    override suspend fun google(googleIdToken: String): GoogleResponse {
        return GoogleResponse("1123")
    }
}