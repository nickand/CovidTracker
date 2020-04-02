package com.example.covidtracker.ui.tips

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
import androidx.navigation.fragment.findNavController
import com.example.covidtracker.R
import com.example.covidtracker.extensions.addFragment
import com.example.covidtracker.ui.detail.DetailActivity
import com.example.covidtracker.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.itemCoranaviruses
import kotlinx.android.synthetic.main.fragment_home.itemPrevent
import kotlinx.android.synthetic.main.fragment_home.itemSymptoms
import kotlinx.android.synthetic.main.fragment_home.itemTransmitted
import kotlinx.android.synthetic.main.fragment_home.tipsOfficersTitle
import kotlinx.android.synthetic.main.fragment_tips.*

class TipsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tips, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customizeTitleApp()

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
        itemWearMask.setOnClickListener {
            goToDetailActivivty(
                requireContext(),
                R.drawable.ic_mask_detail,
                getString(R.string.when_to_wear_a_mask),
                getString(R.string.mask_detail))
        }
        itemDiagnosis.setOnClickListener {
            goToDetailActivivty(
                requireContext(),
                R.drawable.ic_diagnosis_detail,
                getString(R.string.diagnosis_title),
                getString(R.string.diagnosis_detail))
        }
        itemTravel.setOnClickListener {
            goToDetailActivivty(
                requireContext(),
                R.drawable.ic_plane,
                getString(R.string.information_for_travelers),
                getString(R.string.travel_detail))
        }
        itemVaccine.setOnClickListener {
            goToDetailActivivty(
                requireContext(),
                R.drawable.ic_treatment,
                getString(R.string.is_there_a_vaccine_medication_or_treatment),
                getString(R.string.treatment_detail))
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

    private fun customizeTitleApp() {
        val spannable = SpannableString(getString(R.string.tips_officers))
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.red)),
            0, 4,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tipsOfficersTitle.text = spannable
    }
}