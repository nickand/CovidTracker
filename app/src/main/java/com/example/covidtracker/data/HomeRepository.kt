package com.example.covidtracker.data

import com.example.covidtracker.data.model.WorldStats
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val service: ApiClient) {

    suspend fun getWorldStats(): WorldStats = withContext(Dispatchers.IO) {
        service.getWorldStats()
    }
}
