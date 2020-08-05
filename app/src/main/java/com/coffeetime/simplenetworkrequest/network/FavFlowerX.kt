package com.coffeetime.simplenetworkrequest.network


import com.google.gson.annotations.SerializedName

data class FavFlowerX(
    val flower: FlowerX,
    val id: Int,
    @SerializedName("user_id")
    val userId: Int
)