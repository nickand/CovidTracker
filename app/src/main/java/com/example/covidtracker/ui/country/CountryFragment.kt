package com.example.covidtracker.ui.country

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.R
import com.example.covidtracker.extensions.gone
import com.example.covidtracker.extensions.search
import com.example.covidtracker.extensions.visible
import com.example.covidtracker.ui.adapter.CountryWiseAdapter
import kotlinx.android.synthetic.main.fragment_country.*
import kotlinx.android.synthetic.main.fragment_home.progressBar
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class CountryFragment : Fragment(), CountryWiseAdapter.OnEvent {

    private val countryViewModel: CountryViewModel by viewModel()

    private lateinit var listAdapter: CountryWiseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        countryViewModel.model.observe(this, Observer(::updateUi))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_country, container, false)

        listAdapter = CountryWiseAdapter(mutableListOf(), this)

        return view
    }

    private fun updateUi(model: CountryViewModel.UiModel) {
        when (model) {
            is CountryViewModel.UiModel.Loading -> {
                progressBar.visible()
            }
            is CountryViewModel.UiModel.Content -> {
                progressBar.gone()

                model.countryStat?.countryStats?.let { listAdapter.addData(it) }

                setupRecyclerView()

                performSearch()

                Log.d("TAG", "RESPONSE: " + model.countryStat.toString())
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

    private fun performSearch() {
        searchEditText.requestFocus()
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                listAdapter.search(listAdapter.getList(), listAdapter.getOriginalList(), s.toString())
            }

        })
    }

    private fun hideSoftKeyboard(input: EditText) {
        val imm: InputMethodManager = Objects.requireNonNull(activity)
            ?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(input.windowToken, 0)
    }

    override fun logEvent(query: String) {

    }
}