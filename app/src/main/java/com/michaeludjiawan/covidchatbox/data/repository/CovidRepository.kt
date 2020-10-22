package com.michaeludjiawan.covidchatbox.data.repository

import com.michaeludjiawan.covidchatbox.data.api.Result
import com.michaeludjiawan.covidchatbox.data.model.Country
import com.michaeludjiawan.covidchatbox.data.model.GlobalStatistic

interface CovidRepository {
    suspend fun updateData(): Result<Boolean>
    suspend fun getCountryData(countryCode: String): Country?
    suspend fun getGlobalData(): GlobalStatistic
}
