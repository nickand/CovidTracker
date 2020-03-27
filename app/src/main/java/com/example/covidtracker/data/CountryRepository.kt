package com.example.covidtracker.data

import com.example.covidtracker.data.model.CountryWiseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryRepository(private val service: ApiClient) {

    suspend fun getCountryWiseCases(): CountryWiseCase = withContext(Dispatchers.IO) {
        service.getCountryWiseCases()
    }
}
