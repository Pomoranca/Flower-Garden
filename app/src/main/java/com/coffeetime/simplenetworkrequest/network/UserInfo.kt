package com.coffeetime.simplenetworkrequest.network


import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("user")
    val user: UserX
)