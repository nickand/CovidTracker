package com.example.covidtracker.data.model

import com.google.gson.annotations.SerializedName

data class CountryStat(

    @SerializedName("country_name") val countryName: String,
    @SerializedName("cases") val totalCases: String,
    @SerializedName("deaths") val totalDeaths: String,
    @SerializedName("total_recovered") val totalRecovered: String,
    @SerializedName("new_cases") val newCases: String
)