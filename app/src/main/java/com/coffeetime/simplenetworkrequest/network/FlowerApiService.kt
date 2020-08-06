package com.coffeetime.simplenetworkrequest.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://flowrspot-api.herokuapp.com/api/v1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface FlowerApiService {

    /** Get list of all flowers on specific page */
    @GET("flowers")
    suspend fun getAllFlowersAsync(@Query("page") page: Int): Flowers

    /** Get logged in user info */
    @GET("users/me")
    suspend fun getInfo(@Header("Authorization") auth: String): UserInfo

    /** Register user */
    @FormUrlEncoded
    @POST("users/register")
    fun registerUserAsync(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("date_of_birth") dob: String
    ): Call<NetworkResponse>

    /** login user */
    @FormUrlEncoded
    @POST("users/login")
    fun loginUserAsync(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<NetworkResponse>

    /**List favorite flowers */
    @GET("flowers/favorites")
    suspend fun getFavoriteFlowers(@Header("Authorization") auth: String): FavFlower

    /**Mark flower as favorite */
    @POST("flowers/{flower_id}/favorites")
    fun markFavoriteFlower(
        @Path("flower_id") flowerId: Int,
        @Header("Authorization") auth: String
    ): Call<Flower>
}

object FlowerApi {
    val retrofitService: FlowerApiService by lazy {
        retrofit.create(
            FlowerApiService::class.java
        )
    }
}