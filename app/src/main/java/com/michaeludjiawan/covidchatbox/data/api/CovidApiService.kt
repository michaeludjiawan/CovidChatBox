package com.michaeludjiawan.covidchatbox.data.api

import com.michaeludjiawan.covidchatbox.data.model.CountrySummary
import retrofit2.http.GET

interface CovidApiService {

    @GET("summary")
    suspend fun getSummary(): List<CountrySummary>
}