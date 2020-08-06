package com.coffeetime.simplenetworkrequest.network


import com.google.gson.annotations.SerializedName

data class FavFlower(
    @SerializedName("fav_flowers")
    val favFlowers: List<FavFlowerX>

)