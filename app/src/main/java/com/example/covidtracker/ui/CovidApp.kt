package com.example.covidtracker.ui

import android.app.Application
import com.example.covidtracker.R
import com.example.covidtracker.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class CovidApp : Application() {

    companion object {
        lateinit var mApplication: CovidApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        mApplication = this

        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/NunitoSans-SemiBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )

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
