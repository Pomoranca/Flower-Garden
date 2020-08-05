package com.coffeetime.simplenetworkrequest.network


import com.google.gson.annotations.SerializedName

data class FlowerX(
    val description: String,
    val favorite: Boolean,
    val features: List<String>,
    val id: Int,
    @SerializedName("latin_name")
    val latinName: String,
    val name: String,
    @SerializedName("profile_picture")
    val profilePicture: String,
    val sightings: Int
)