package com.example.covidtracker.extensions

import android.app.Application
import com.example.covidtracker.di.*
import com.example.covidtracker.util.CrashHandler
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CovidApp : Application() {

    companion object {
        lateinit var mApplication: CovidApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler(CrashHandler(this))
        mApplication = this

        startKoin {
            androidContext(this@CovidApp)
            modules (listOf(
                appModule,
                viewModelModule,
                networkModule,
                repositoryModule
            ))
        }
    }
}
