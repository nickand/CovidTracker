package com.example.covidtracker.data.api

import com.example.covidtracker.BuildConfig
import com.example.covidtracker.data.ApiClient
import com.example.covidtracker.util.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun getMoshiConverterFactory() = MoshiConverterFactory.create(
    Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build())

fun getLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

fun getOkHttpClient(vararg interceptors: Interceptor): OkHttpClient {
    val client = OkHttpClient.Builder()

    client.connectTimeout(30, TimeUnit.SECONDS)
    client.writeTimeout(30, TimeUnit.SECONDS)
    client.readTimeout(30, TimeUnit.SECONDS)

    interceptors.forEach {
        client.interceptors().add(it)
    }
    return client.build()
}

fun getApiService(): ApiClient {

    val headerInterceptor = Interceptor { chain ->
        chain.proceed(chain.request().newBuilder().build())
    }

    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient(getLoggingInterceptor(), headerInterceptor))
        .build()
        .create(ApiClient::class.java)
}