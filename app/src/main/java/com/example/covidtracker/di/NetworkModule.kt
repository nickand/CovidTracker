package com.example.covidtracker.di

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.covidtracker.BuildConfig
import com.example.covidtracker.data.ApiClient
import com.example.covidtracker.data.api.getApiService
import com.example.covidtracker.util.Constants
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single(named("default")) { getOkHttpClientBuilder() }
    factory(named("timeout")) { (timeout: Long) -> getOkHttpClientBuilder(timeout) }
    factory { (baseUrl: String) -> getRetrofitBuilder(baseUrl) }

    single { getApiService() }
    single { getGlideUrl() }
}

fun getGlideUrl(): GlideUrl {
    return GlideUrl(Constants.MASK_INSTRUCTIONS_URL, LazyHeaders.Builder()
        .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
        .addHeader("x-rapidapi-key", Constants.API_KEY)
        .build()
    )
}

internal fun getOkHttpClientBuilder(timeout: Long = 60L) : OkHttpClient.Builder =
        OkHttpClient.Builder().apply {
            connectTimeout(timeout, TimeUnit.SECONDS)
            readTimeout(timeout, TimeUnit.SECONDS)
            writeTimeout(timeout, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            addInterceptor { chain ->
                val request = chain.request().newBuilder().apply {
                    addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
                    addHeader("x-rapidapi-key", Constants.API_KEY)
                }.build()
                chain.proceed(request)
            }
        }

internal fun getRetrofitBuilder(baseUrl: String) =
        Retrofit.Builder().apply {
            baseUrl(baseUrl)
            addCallAdapterFactory(CoroutineCallAdapterFactory())
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        }