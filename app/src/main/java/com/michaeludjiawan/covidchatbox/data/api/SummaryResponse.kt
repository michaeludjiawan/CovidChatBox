package com.michaeludjiawan.covidchatbox.data.api

import com.google.gson.annotations.SerializedName
import com.michaeludjiawan.covidchatbox.data.model.Country
import com.michaeludjiawan.covidchatbox.data.model.StatusStatistic

data class SummaryResponse(
    @SerializedName("global") val globalStatistic: StatusStatistic,
    @SerializedName("countries") val countries: List<Country>
)