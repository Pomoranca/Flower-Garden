package com.coffeetime.simplenetworkrequest.network.models

import com.google.gson.annotations.SerializedName

data class NetworkResponse(
    @SerializedName("auth_token")
    val authToken: String,
    val error: String
)
