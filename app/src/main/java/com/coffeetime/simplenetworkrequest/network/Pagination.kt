package com.coffeetime.simplenetworkrequest.network


import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("next_page")
    val nextPage: Any,
    @SerializedName("prev_page")
    val prevPage: Any,
    @SerializedName("total_pages")
    val totalPages: Int
)