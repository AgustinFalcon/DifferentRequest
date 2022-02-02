package com.example.jugandopostrequest.api

import com.example.jugandopostrequest.model.Post
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    //A simple get request
    @GET("posts/1")
    suspend fun getPost(@Header("Auth") auth: String): Response<Post>


    //For filter a specific id
    @GET("posts/{postNumber}")
    suspend fun getPost2(@Path("postNumber") number: Int): Response<Post>

    //For filter a list of ids
    @GET("posts")
    suspend fun getCustomPost(
        @Query("userId") userId: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): Response<List<Post>>

    //It's the same but with HashMap
    @GET("posts")
    suspend fun getCustomPost2(
        @Query("userId") userId: Int,
        @QueryMap options: Map<String, String>
    ): Response<List<Post>>



    @POST("posts")
    suspend fun pushPost(@Body post: Post): Response<Post>


    @FormUrlEncoded
    @POST("posts")
    suspend fun pushPost2(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ): Response<Post>
}