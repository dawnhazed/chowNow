package com.dicoding.chownow.data.remote.retrofit

import com.dicoding.chownow.data.remote.response.LoginResponse
import com.dicoding.chownow.data.remote.response.RegisterResponse
import com.dicoding.chownow.data.remote.response.RestoResponse
import com.dicoding.chownow.data.remote.response.ReviewResponse
import com.dicoding.chownow.data.remote.response.ReviewResponseItem
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    /* @FormUrlEncoded
    @POST("recommend/{userId}")
    fun recommend(
        @Field("userId") userId: Int,
    ): Call<RecResponse> */

    @GET("reviews")
    fun reviews() : Call<List<ReviewResponseItem>>
}