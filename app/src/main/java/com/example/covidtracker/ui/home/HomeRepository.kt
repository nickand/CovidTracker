package com.example.covidtracker.ui.home

import com.example.covidtracker.data.ApiClient
import com.example.covidtracker.data.model.WorldStats
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeRepository(private val service: ApiClient) {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    suspend fun getWorldStats(): WorldStats = withContext(Dispatchers.IO) {
        service.getWorldStats()
    }

    suspend fun getCountryWiseCases() {
        ioScope.launch {
            service.getCountryWiseCases()
        }
    }
}
