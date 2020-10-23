package com.michaeludjiawan.covidchatbox.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GlobalStatistic(
    @SerializedName("NewConfirmed") val newConfirmed: Int,
    @SerializedName("TotalConfirmed") val totalConfirmed: Int,
    @SerializedName("NewDeaths") val newDeaths: Int,
    @SerializedName("TotalDeaths") val totalDeaths: Int,
    @SerializedName("NewRecovered") val newRecovered: Int,
    @SerializedName("TotalRecovered") val totalRecovered: Int
) {
    @PrimaryKey
    var id = 0
}