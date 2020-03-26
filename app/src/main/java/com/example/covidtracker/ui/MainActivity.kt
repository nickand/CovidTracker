package com.example.covidtracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covidtracker.R
import com.example.covidtracker.extensions.selectTab
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initHome()
    }

    private fun initHome() {
        navigationFragment.selectTab(R.id.homeFragment)
    }
}
