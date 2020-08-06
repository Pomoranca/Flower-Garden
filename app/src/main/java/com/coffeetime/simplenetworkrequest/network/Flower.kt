package com.coffeetime.simplenetworkrequest.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Flower(
    val id: Int,
    val name: String,
    @SerializedName("latin_name")
    val latinName: String,
    @SerializedName("profile_picture")
    val profilePicture: String,
    val favorite: Boolean
) : Parcelable