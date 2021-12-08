package com.vayuzassignment.data.retrofit

import com.vayuzassignment.data.model.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface CustomApi {

    @GET("breeds/image/random")
    suspend fun getImages(): Response

}