package com.example.covidtracker.ui.country

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.extensions.gone
import com.example.covidtracker.extensions.visible
import com.example.covidtracker.ui.adapter.CountryWiseAdapter
import kotlinx.android.synthetic.main.fragment_country.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.progressBar
import org.koin.android.viewmodel.ext.android.viewModel

class CountryFragment : Fragment(), CountryWiseAdapter.OnEvent {

    private val countryViewModel: CountryViewModel by viewModel()

    private val listAdapter by lazy { CountryWiseAdapter(mutableListOf(), this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        countryViewModel.model.observe(this, Observer(::updateUi))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    private fun updateUi(model: CountryViewModel.UiModel) {
        when (model) {
            is CountryViewModel.UiModel.Loading -> {
                progressBar.visible()
            }
            is CountryViewModel.UiModel.Content -> {
                progressBar.gone()

                listAdapter.addData(model.countryStat.countryStats)

                setupRecyclerView()

                Log.d("TAG", "RESPONSE: "+model.countryStat.toString())
            }
            is CountryViewModel.UiModel.Navigation -> {

            }
            CountryViewModel.UiModel.ShowUi -> {
                countryViewModel.getCountryWiseCases()
            }
        }
    }

    private fun setupRecyclerView() {
        countryWiseRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }
    }

    override fun logEvent(query: String) {

    }
}