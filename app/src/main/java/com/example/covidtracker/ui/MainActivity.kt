package com.example.covidtracker.ui

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.covidtracker.R
import com.example.covidtracker.extensions.selectTab
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    initHome()
                }
                R.id.action_tip -> {
                    initTips()
                }
                R.id.action_worldwide -> {
                    initCountry()
                }
            }
            true
        }
    }

    private fun initCountry() {
        navigationFragment.selectTab(R.id.countryFragment)
    }

    private fun initTips() {
        navigationFragment.selectTab(R.id.tipsFragment)
    }

    private fun initHome() {
        navigationFragment.selectTab(R.id.homeFragment)
    }

    override fun onBackPressed() {
        val bottomNavigationView =
            findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        val selectedItemId = bottomNavigationView.selectedItemId
        if (R.id.action_home != selectedItemId || R.id.action_home == selectedItemId) {
            finish()
        }
    }
}
