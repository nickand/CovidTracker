package com.example.covidtracker.di

import com.example.covidtracker.data.api.getApiService
import com.example.covidtracker.data.api.getGlideUrl
import org.koin.dsl.module

val networkModule = module {
    single { getApiService() }
    single { getGlideUrl() }
}