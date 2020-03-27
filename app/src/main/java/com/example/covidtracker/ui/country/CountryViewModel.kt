package com.example.covidtracker.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidtracker.data.CountryRepository
import com.example.covidtracker.data.model.CountryStat
import com.example.covidtracker.data.model.CountryWiseCase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CountryViewModel(private val countryRepository: CountryRepository) : ViewModel(),
    CoroutineScope {

    private var job: Job = Job()

    private val countryWiseCasesList = MutableLiveData<List<CountryStat>?>()
    val countryWiseCasesResponse: LiveData<List<CountryStat>?>
        get() = countryWiseCasesList

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
        class Content(val countryStat: CountryWiseCase) : UiModel()
        class Navigation(val countryStat: CountryStat) : UiModel()
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

    fun getCountryWiseCases() {
        launch {
            uiModel.value = UiModel.Loading
            uiModel.value = UiModel.Content(countryRepository.getCountryWiseCases())
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}