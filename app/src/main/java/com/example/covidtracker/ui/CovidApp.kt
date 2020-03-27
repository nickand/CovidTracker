package com.example.covidtracker.ui

import android.app.Application
import com.example.covidtracker.di.appModule
import com.example.covidtracker.di.networkModule
import com.example.covidtracker.di.repositoryModule
import com.example.covidtracker.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CovidApp : Application() {

    companion object {
        lateinit var mApplication: CovidApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        mApplication = this

        startKoin {
            androidContext(this@CovidApp)
            modules(
                listOf(
                    appModule,
                    viewModelModule,
                    networkModule,
                    repositoryModule
                )
            )
        }
    }
}
