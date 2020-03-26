package com.example.covidtracker.data.model

import com.google.gson.annotations.SerializedName

data class CountryWiseCase(

    @SerializedName("countries_stat") val countryStats: List<CountryStat>
)