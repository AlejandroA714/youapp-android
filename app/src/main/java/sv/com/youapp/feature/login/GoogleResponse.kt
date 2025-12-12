package sv.com.youapp.feature.login

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GoogleResponse(val sid: String)