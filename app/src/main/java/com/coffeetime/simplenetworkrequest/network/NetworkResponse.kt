package com.coffeetime.simplenetworkrequest.network

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import java.io.IOException

data class NetworkResponse(
    @SerializedName("auth_token")
    val authToken: String,
    val error: String
)
