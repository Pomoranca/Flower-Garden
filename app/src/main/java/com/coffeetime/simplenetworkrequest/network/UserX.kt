package com.coffeetime.simplenetworkrequest.network


import com.google.gson.annotations.SerializedName

data class UserX(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String
)