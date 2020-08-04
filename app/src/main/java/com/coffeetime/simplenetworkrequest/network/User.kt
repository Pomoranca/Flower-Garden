package com.coffeetime.simplenetworkrequest.network

import com.squareup.moshi.Json

data class User(
    val id: Int,
    val email: String,
    val password: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    @Json(name = "date_of_birth")
    val dateOfBirth: String
)