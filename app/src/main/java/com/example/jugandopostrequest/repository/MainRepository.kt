package com.example.jugandopostrequest.repository

import com.example.jugandopostrequest.api.RetrofitInstance
import com.example.jugandopostrequest.model.Post
import retrofit2.Response


class MainRepository {

    suspend fun getPost(auth: String): Response<Post> {
        return RetrofitInstance.api.getPost(auth)
    }


    suspend fun getPost2(number: Int): Response<Post> {
        return RetrofitInstance.api.getPost2(number)
    }

    suspend fun getCustomPost(userId: Int, sort: String, order: String): Response<List<Post>> {
        return RetrofitInstance.api.getCustomPost(userId, sort, order)
    }

    suspend fun getCustomPost2(userId: Int, options: Map<String, String>): Response<List<Post>>{
        return RetrofitInstance.api.getCustomPost2(userId, options)
    }

    suspend fun pushPost(post: Post): Response<Post> {
        return RetrofitInstance.api.pushPost(post)
    }

    suspend fun pushPost2(userId: Int, id: Int, title: String, body: String): Response<Post> {
        return RetrofitInstance.api.pushPost2(userId, id, title, body)
    }
}