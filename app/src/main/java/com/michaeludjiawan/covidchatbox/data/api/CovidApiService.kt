package com.michaeludjiawan.covidchatbox.data.api

import retrofit2.http.GET

interface CovidApiService {

    @GET("summary")
    suspend fun getSummary(): SummaryResponse
}