package com.example.jugandopostrequest.api

import com.example.jugandopostrequest.MyInterceptor
import com.example.jugandopostrequest.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

}