package com.example.covidtracker.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.covidtracker.R
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment(var bundle: Bundle) : Fragment() {

    companion object {
        const val DETAIL = "detail"
        const val IMAGE = "image"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
    }

    private var image: Int = 0
    private lateinit var title: String
    private lateinit var description: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!bundle.isEmpty) {
            bundle = requireActivity().intent.getBundleExtra(DETAIL)!!
            image = bundle.getInt(IMAGE)
            title = bundle.getString(TITLE).orEmpty()
            description = bundle.getString(DESCRIPTION).orEmpty()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iconDetail.setImageResource(image)
        titleDetail.text = title
        descriptionDetail.text = description

        closeImage.setOnClickListener {
            requireActivity().finish()
        }
    }
}