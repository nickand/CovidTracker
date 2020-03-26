package com.example.covidtracker.di

import com.example.covidtracker.ui.home.HomeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { HomeRepository(get()) }
}