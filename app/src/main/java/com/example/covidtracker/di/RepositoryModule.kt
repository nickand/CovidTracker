package com.example.covidtracker.di

import com.example.covidtracker.data.CountryRepository
import com.example.covidtracker.data.HomeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { HomeRepository(get()) }
    single { CountryRepository(get()) }
}