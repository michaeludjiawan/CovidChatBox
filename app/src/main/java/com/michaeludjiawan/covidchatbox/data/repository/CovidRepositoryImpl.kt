package com.michaeludjiawan.covidchatbox.data.repository

import com.michaeludjiawan.covidchatbox.data.api.CovidApiService
import com.michaeludjiawan.covidchatbox.data.api.Result
import com.michaeludjiawan.covidchatbox.data.local.AppDb
import com.michaeludjiawan.covidchatbox.data.local.AppPreferences
import com.michaeludjiawan.covidchatbox.data.model.CountrySummary

const val API_LAST_FETCH_TIMESTAMP = "api_last_fetch_timestamp"
const val API_CACHE_DURATION = 86400000 //24 hours in ms

class CovidRepositoryImpl(
    private val apiService: CovidApiService,
    private val appDb: AppDb,
    private val appPref: AppPreferences
) : CovidRepository {

    override suspend fun getSummary(): Result<List<CountrySummary>> {
        return if (!isCacheValid()) {
            getSummaryFromServer()
        } else {
            val summary = getSummaryFromDb()
            Result.Success(summary)
        }
    }

    // Cache is invalid if duration exceed API_CACHE_DURATION or doesn't exist
    private fun isCacheValid(): Boolean {
        if (!appPref.contains(API_LAST_FETCH_TIMESTAMP)) return false

        val lastFetch = appPref.getLong(API_LAST_FETCH_TIMESTAMP)
        val elapsedTime = System.currentTimeMillis() - lastFetch

        return elapsedTime < API_CACHE_DURATION
    }

    private suspend fun getSummaryFromDb(): List<CountrySummary> = appDb.summaryDao().getSummary()

    private suspend fun getSummaryFromServer(): Result<List<CountrySummary>> {
        return try {
            val summary = apiService.getSummary()
            saveSummaryToDb(summary)
            appPref.save(API_LAST_FETCH_TIMESTAMP, System.currentTimeMillis())
            Result.Success(summary)
        } catch (throwable: Throwable) {
            Result.Error(throwable)
        }
    }

    private suspend fun saveSummaryToDb(summary: List<CountrySummary>) {
        appDb.summaryDao().insertAll(summary)
    }
}