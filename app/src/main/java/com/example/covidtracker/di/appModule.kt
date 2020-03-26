package com.example.covidtracker.di

import com.google.gson.Gson
import org.koin.dsl.module

var appModule = module {
    // Gson creation is heavy.  Keep an instance
    single { Gson() }
}