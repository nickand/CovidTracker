package com.example.covidtracker.ui.country

import com.example.covidtracker.data.ApiClient
import com.example.covidtracker.data.model.CountryWiseCase
import com.example.covidtracker.data.model.WorldStats
import com.example.covidtracker.extensions.safeApiCall
import com.example.covidtracker.util.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryRepository(private val service: ApiClient) {

    suspend fun getCountryWiseCases(): ResultWrapper<CountryWiseCase>? {
        var networkResult: ResultWrapper<CountryWiseCase>? = null

        safeApiCall( { service.getCountryWiseCases() },
            { networkResult = it },
            { networkResult = it }
        )

        return networkResult
    }
}
