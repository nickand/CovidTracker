package com.example.covidtracker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidtracker.data.model.WorldStats
import com.example.covidtracker.util.ResultWrapper
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

    private var worldStat: WorldStats? = null
    private var errorWorldStats: String? = null

    private val _errorLiveData = MutableLiveData<String?>()
    val errorLiveData: LiveData<String?>
        get() = _errorLiveData

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val worldStat: WorldStats?) : UiModel()
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

    fun getWorldStats() {
        launch {
            uiModel.value = UiModel.Loading

            when (val worldStatsResponse = homeRepository.getWorldStats()) {
                is ResultWrapper.GenericError -> {
                    val responseData = worldStatsResponse.error?.message
                    errorWorldStats = responseData
                }
                is ResultWrapper.Success -> {
                    val responseData = worldStatsResponse.value
                    worldStat = responseData
                }
            }

            uiModel.value = UiModel.Content(worldStat)
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

}