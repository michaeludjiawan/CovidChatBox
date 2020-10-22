package com.michaeludjiawan.covidchatbox.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class CountrySummary(
    @SerializedName("Country") val country: String,
    @SerializedName("CountryCode") @PrimaryKey val countryCode: String,
    @SerializedName("Slug") val slug: String,
    @SerializedName("NewConfirmed") val newConfirmed: Int,
    @SerializedName("TotalConfirmed") val totalConfirmed: Int,
    @SerializedName("NewDeaths") val newDeaths: Int,
    @SerializedName("TotalDeaths") val totalDeaths: Int,
    @SerializedName("NewRecovered") val newRecovered: Int,
    @SerializedName("TotalRecovered") val totalRecovered: Int,
    @SerializedName("Date") val date: Date
)