package com.example.covidtracker.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.covidtracker.R
import com.example.covidtracker.di.getGlideUrl
import com.example.covidtracker.extensions.gone
import com.example.covidtracker.extensions.visible
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel.model.observe(this, Observer(::updateUi))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun loadBanner() {
        Glide.with(this)
            .load(getGlideUrl())
            .into(bannerImage)
    }

    private fun updateUi(model: HomeViewModel.UiModel) {
        when (model) {
            is HomeViewModel.UiModel.Loading -> {
                progressBar.visible()
            }
            is HomeViewModel.UiModel.Content -> {
                progressBar.gone()

                loadBanner()

                Log.d("TAG", "RESPONSE: "+model.worldStat.toString())
            }
            is HomeViewModel.UiModel.Navigation -> {

            }
            HomeViewModel.UiModel.ShowUi -> {
                homeViewModel.getWorldStats()
            }
        }
    }
}
