package com.example.covidtracker.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.covidtracker.R
import com.example.covidtracker.data.api.getGlideUrl
import com.example.covidtracker.extensions.gone
import com.example.covidtracker.extensions.loadGlideUrl
import com.example.covidtracker.extensions.setRoundCorners
import com.example.covidtracker.extensions.visible
import com.example.covidtracker.ui.detail.DetailActivity
import com.example.covidtracker.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_total_cases.*
import kotlinx.android.synthetic.main.item_total_death.*
import kotlinx.android.synthetic.main.item_total_recovered.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemCoranaviruses.setOnClickListener {
            goToDetailActivivty(
                requireContext(),
                R.drawable.ic_bacteria,
                getString(R.string.what_are_coronaviruses_subtitle),
                getString(R.string.coronaviruses_detail))
        }
        itemSymptoms.setOnClickListener {
            goToDetailActivivty(
                requireContext(),
                R.drawable.ic_symptoms,
                getString(R.string.what_are_the_symptoms_subtitle),
                getString(R.string.symptoms_detail))
        }
        itemTransmitted.setOnClickListener {
            goToDetailActivivty(
                requireContext(),
                R.drawable.ic_transmitted,
                getString(R.string.how_is_it_transmitted),
                getString(R.string.transmitted_detail))
        }
        itemPrevent.setOnClickListener {
            goToDetailActivivty(
                requireContext(),
                R.drawable.ic_hands,
                getString(R.string.how_prevent),
                getString(R.string.prevent_detail))
        }
    }

    private fun goToDetailActivivty(
        context: Context, image: Int,
        title: String, description: String) {
        val intent = Intent(context, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putInt(DetailFragment.IMAGE, image)
        bundle.putString(DetailFragment.TITLE, title)
        bundle.putString(DetailFragment.DESCRIPTION, description)
        intent.putExtra(DetailFragment.DETAIL, bundle)
        startActivity(intent)
    }

    private fun loadBanner() {
        bannerImage.loadGlideUrl(getGlideUrl())
        bannerImage.setRoundCorners(R.dimen.spacing_xs)
    }

    private fun updateUi(model: HomeViewModel.UiModel) {
        when (model) {
            is HomeViewModel.UiModel.Loading -> {
                progressBar.visible()
            }
            is HomeViewModel.UiModel.Content -> {
                progressBar.gone()

                loadBanner()
                customizeTitleApp()

                txtCasesSubtitle.text = model.worldStat?.totalCases
                txtRecoveredSubtitle.text = model.worldStat?.totalRecovered
                txtDeathSubtitle.text = model.worldStat?.totalDeath
            }
            HomeViewModel.UiModel.ShowUi -> {
                homeViewModel.getWorldStats()
            }
        }
    }

    private fun customizeTitleApp() {
        val spannable = SpannableString(getString(R.string.app_title))
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.red)),
            0, 11,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        appTitle.text = spannable
    }
}
