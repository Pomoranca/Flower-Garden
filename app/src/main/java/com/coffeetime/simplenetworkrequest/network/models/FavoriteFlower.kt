package com.coffeetime.simplenetworkrequest.network.models


import com.google.gson.annotations.SerializedName

data class FavoriteFlower(
    val flower: FlowerX,
    val id: Int,
    @SerializedName("user_id")
    val userId: Int
)