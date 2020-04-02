package com.example.covidtracker.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.covidtracker.R
import com.example.covidtracker.extensions.addFragment

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initDetail()
    }

    private fun initDetail() {
        intent.getBundleExtra(DetailFragment.DETAIL)?.let { DetailFragment(it) }?.let {
            addFragment(supportFragmentManager, R.id.fragmentContainer,
                it
            )
        }
    }
}
