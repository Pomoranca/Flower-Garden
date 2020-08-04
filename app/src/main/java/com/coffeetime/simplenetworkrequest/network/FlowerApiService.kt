package com.coffeetime.simplenetworkrequest.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://flowrspot-api.herokuapp.com/api/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface FlowerApiService {
    @GET("flowers")
    fun getAllFlowersAsync(@Query("page") page: Int): Deferred<Flowers>


    @GET("users/me")
    fun getInfo(@Header("Authorization") Authorization: String): Deferred<UserInfo>

    @FormUrlEncoded
    @POST("users/register")
    fun registerUserAsync(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("date_of_birth") dob: String
    ): Call<NetworkResponse>

    @FormUrlEncoded
    @POST("users/login")
    fun loginUserAsync(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<NetworkResponse>
}

object FlowerApi {
    val retrofitService: FlowerApiService by lazy {
        retrofit.create(
            FlowerApiService::class.java
        )
    }
}