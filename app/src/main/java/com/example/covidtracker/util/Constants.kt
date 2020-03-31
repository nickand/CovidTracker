package com.example.covidtracker.util

import com.example.covidtracker.BuildConfig

object Constants{

    const val BASE_URL = "https://coronavirus-monitor.p.rapidapi.com/"
    const val API_KEY = BuildConfig.API_KEY
    const val MASK_INSTRUCTIONS_URL = BASE_URL + "coronavirus/masks.php"
}