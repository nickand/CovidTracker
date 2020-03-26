package com.example.covidtracker.data.api

import com.example.covidtracker.data.ApiClient
import com.example.covidtracker.util.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getOkHttpClient(): OkHttpClient {

    val interceptor = Interceptor { chain ->
        val request =
            chain.request().newBuilder()
                .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
                .addHeader("x-rapidapi-key", Constants.API_KEY)

        val actualRequest = request.build()
        chain.proceed(actualRequest)
    }

    return HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}

fun getApiService(): ApiClient {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient())
        .build().run {
            create(ApiClient::class.java)
        }
}