package com.example.covidtracker.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidtracker.data.model.CountryStat
import com.example.covidtracker.data.model.CountryWiseCase
import com.example.covidtracker.util.ResultWrapper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CountryViewModel(private val countryRepository: CountryRepository) : ViewModel(),
    CoroutineScope {

    private var job: Job = Job()

    private var countryWiseCases: CountryWiseCase? = null
    private var errorCountryWiseCases: String? = null

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
        class Content(val countryStat: CountryWiseCase?) : UiModel()
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

            when (val countryResponse = countryRepository.getCountryWiseCases()) {
                is ResultWrapper.GenericError -> {
                    val responseData = countryResponse.error?.message
                    errorCountryWiseCases = responseData
                }
                is ResultWrapper.Success -> {
                    val responseData = countryResponse.value
                    countryWiseCases = responseData
                }
            }

            uiModel.value = UiModel.Content(countryWiseCases)
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}