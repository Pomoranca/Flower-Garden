package com.coffeetime.simplenetworkrequest.network.models


import com.coffeetime.simplenetworkrequest.network.models.FavoriteFlower
import com.google.gson.annotations.SerializedName

data class FavoriteFlowerList(
    @SerializedName("fav_flowers")
    val favFlowers: List<FavoriteFlower>

)