package com.michaeludjiawan.covidchatbox.data.api

import com.google.gson.annotations.SerializedName
import com.michaeludjiawan.covidchatbox.data.model.Country
import com.michaeludjiawan.covidchatbox.data.model.StatusStatistic

data class SummaryResponse(
    @SerializedName("Global") val globalStatistic: StatusStatistic,
    @SerializedName("Countries") val countries: List<Country>
)