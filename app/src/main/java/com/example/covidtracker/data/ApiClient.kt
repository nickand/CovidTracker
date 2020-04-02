package com.example.covidtracker.data

import com.example.covidtracker.data.model.CountryWiseCase
import com.example.covidtracker.data.model.WorldStats
import retrofit2.http.GET

interface ApiClient {

    @GET("coronavirus/cases_by_country.php")
    suspend fun getCountryWiseCases(): CountryWiseCase

    @GET("coronavirus/worldstat.php")
    suspend fun getWorldStats(): WorldStats
}