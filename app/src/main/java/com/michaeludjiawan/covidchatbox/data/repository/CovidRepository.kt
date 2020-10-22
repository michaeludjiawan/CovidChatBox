package com.michaeludjiawan.covidchatbox.data.repository

import com.michaeludjiawan.covidchatbox.data.api.Result
import com.michaeludjiawan.covidchatbox.data.model.CountrySummary

interface CovidRepository {
    suspend fun getSummary(): Result<List<CountrySummary>>
}