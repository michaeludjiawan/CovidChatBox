package com.michaeludjiawan.covidchatbox.data.api

import com.google.gson.annotations.SerializedName
import com.michaeludjiawan.covidchatbox.data.model.Country
import com.michaeludjiawan.covidchatbox.data.model.GlobalStatistic
import java.util.*

data class SummaryResponse(
    @SerializedName("Global") val globalStatistic: GlobalStatistic,
    @SerializedName("Countries") val countries: List<Country>,
    @SerializedName("Date") val date: Date
)