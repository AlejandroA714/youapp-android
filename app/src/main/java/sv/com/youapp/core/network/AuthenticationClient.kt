package sv.com.youapp.core.network

import retrofit2.http.POST

interface AuthenticationClient {

    @POST("")
    suspend fun google(): Unit

}