package com.example.covidtracker.di

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.covidtracker.data.api.getApiService
import com.example.covidtracker.util.Constants
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { (baseUrl: String) -> getRetrofitBuilder(baseUrl) }

    single { getApiService() }
    single { getGlideUrl() }
}

fun getGlideUrl(): GlideUrl {
    return GlideUrl(
        Constants.MASK_INSTRUCTIONS_URL, LazyHeaders.Builder()
            .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
            .addHeader("x-rapidapi-key", Constants.API_KEY)
            .build()
    )
}

internal fun getRetrofitBuilder(baseUrl: String) =
    Retrofit.Builder().apply {
        baseUrl(baseUrl)
        addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        addCallAdapterFactory(CoroutineCallAdapterFactory())
    }