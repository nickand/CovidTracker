package com.example.covidtracker.ui.tips

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
            findNavController().navigate(R.id.action_tipsFragment_to_coronavirusesDetailFragment2)
        }
        itemSymptoms.setOnClickListener {
            findNavController().navigate(R.id.action_tipsFragment_to_symptomsDetailFragment2)
        }
        itemTransmitted.setOnClickListener {
            findNavController().navigate(R.id.action_tipsFragment_to_transmittedDetailFragment2)
        }
        itemPrevent.setOnClickListener {
            findNavController().navigate(R.id.action_tipsFragment_to_preventDetailFragment2)
        }
        itemWearMask.setOnClickListener {
            findNavController().navigate(R.id.action_tipsFragment_to_maskDetailFragment)
        }
        itemDiagnosis.setOnClickListener {
            findNavController().navigate(R.id.action_tipsFragment_to_diagnosisDetailFragment)
        }
        itemTravel.setOnClickListener {
            findNavController().navigate(R.id.action_tipsFragment_to_travelDetailFragment)
        }
        itemVaccine.setOnClickListener {
            findNavController().navigate(R.id.action_tipsFragment_to_treatmentDetailFragment)
        }
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