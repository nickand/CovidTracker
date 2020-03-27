package com.example.covidtracker.ui.country

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.covidtracker.R
import com.example.covidtracker.extensions.selectTab
import com.example.covidtracker.ui.MainActivity
import com.example.covidtracker.ui.tips.TipsActivity
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class CountryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

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
        navigationFragment.selectTab(R.id.countryFragment)
    }

    private fun initHome() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(0, 0)
    }

    private fun initSearch() {
        startActivity(Intent(this, TipsActivity::class.java))
        overridePendingTransition(0, 0)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}