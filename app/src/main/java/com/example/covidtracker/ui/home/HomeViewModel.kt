package com.example.covidtracker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidtracker.data.model.WorldStats
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel(), CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val uiModel = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (uiModel.value == null) refresh()
            return uiModel
        }

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val worldStat: WorldStats) : UiModel()
        class Navigation(val worldStat: WorldStats) : UiModel()
        object ShowUi : UiModel()
    }

    init {
        job = SupervisorJob()
    }

    private fun refresh() {
        uiModel.value = UiModel.ShowUi
    }

    private fun destroyScope() {
        job.cancel()
    }

    suspend fun getWorldStats() {
        launch {
            uiModel.value = UiModel.Loading
            uiModel.value = UiModel.Content(homeRepository.getWorldStats())
        }
    }

    suspend fun getCountryWiseCases() {
        return homeRepository.getCountryWiseCases()
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

}