package com.vayuzassignment.data.di

import com.google.gson.GsonBuilder
import com.vayuzassignment.BuildConfig
import com.vayuzassignment.data.retrofit.HomeRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single { provideRetrofit() }
    single { HomeRepository(get()) }
}

fun provideRetrofit(): Retrofit {
    val gson = GsonBuilder().create()
    return Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
        .client(provideOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
}

fun provideOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient()
        .newBuilder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)

    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    builder.addInterceptor(loggingInterceptor)

    return builder.build()
}

