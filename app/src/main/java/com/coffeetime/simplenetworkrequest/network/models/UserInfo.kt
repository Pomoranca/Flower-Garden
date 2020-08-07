package com.coffeetime.simplenetworkrequest.network.models


import com.coffeetime.simplenetworkrequest.network.models.User
import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("user")
    val user: User
)