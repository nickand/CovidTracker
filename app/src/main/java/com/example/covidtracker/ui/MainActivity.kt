package com.example.covidtracker.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.covidtracker.R
import com.example.covidtracker.extensions.selectTab
import com.example.covidtracker.ui.country.CountryActivity
import com.example.covidtracker.ui.tips.TipsActivity
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


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
                    initSearch()
                }
                R.id.action_worldwide -> {
                    initCountry()
                }
            }
            true
        }
    }

    private fun initCountry() {
        startActivity(Intent(this, CountryActivity::class.java))
        overridePendingTransition(0, 0)
    }

    private fun initSearch() {
        startActivity(Intent(this, TipsActivity::class.java))
        overridePendingTransition(0, 0)
    }

    private fun initHome() {
        navigationFragment.selectTab(R.id.homeFragment)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}
