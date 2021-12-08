package com.vayuzassignment.data.retrofit

import retrofit2.Retrofit

class HomeRepository(private val retrofit: Retrofit) {
    val service = retrofit.create(CustomApi::class.java)
    suspend fun getImages() = service.getImages();
}