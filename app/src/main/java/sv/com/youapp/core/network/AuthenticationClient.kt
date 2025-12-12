package sv.com.youapp.core.network

import retrofit2.http.Body
import retrofit2.http.POST
import sv.com.youapp.feature.login.GoogleResponse

interface AuthenticationClient {

    @POST("/oauth2/google")
    suspend fun google(@Body googleIdToken: String): GoogleResponse

}