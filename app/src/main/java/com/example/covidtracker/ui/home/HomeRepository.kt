package com.example.covidtracker.ui.home

import com.example.covidtracker.data.ApiClient
import com.example.covidtracker.data.model.WorldStats
import com.example.covidtracker.extensions.safeApiCall
import com.example.covidtracker.util.ResultWrapper

class HomeRepository(private val service: ApiClient) {

    suspend fun getWorldStats(): ResultWrapper<WorldStats>? {
        var networkResult: ResultWrapper<WorldStats>? = null

        safeApiCall( { service.getWorldStats() },
            { networkResult = it },
            { networkResult = it }
        )

        return networkResult
    }
}
