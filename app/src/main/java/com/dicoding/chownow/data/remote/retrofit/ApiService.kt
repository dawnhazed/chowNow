package com.dicoding.chownow.data.remote.retrofit

import com.dicoding.chownow.data.remote.response.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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

    @POST("recommend/{userId}")
    fun recommend(
        @Path("userId") userId: Int
    ): Call<RecommendResponse>

    @GET("reviews")
    fun reviews(): Call<List<ReviewResponseItem>>

    @GET("search")
    fun search(
        @Query("query") query: String
    ): Call<SearchResponse>

    @GET("menu/search")
    fun searchMenu(
        @Query("query") query: String
    ): Call<MenuResponse>
}
