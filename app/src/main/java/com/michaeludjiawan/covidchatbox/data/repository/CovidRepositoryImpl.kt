package com.michaeludjiawan.covidchatbox.data.repository

import com.michaeludjiawan.covidchatbox.data.api.CovidApiService
import com.michaeludjiawan.covidchatbox.data.api.Result
import com.michaeludjiawan.covidchatbox.data.local.AppDb
import com.michaeludjiawan.covidchatbox.data.local.AppPreferences
import com.michaeludjiawan.covidchatbox.data.model.Country
import com.michaeludjiawan.covidchatbox.data.model.GlobalStatistic
import java.util.*

const val API_LAST_FETCH_TIMESTAMP = "api_last_fetch_timestamp"
const val API_CACHE_DURATION = 86400000 //24 hours in ms

class CovidRepositoryImpl(
    private val apiService: CovidApiService,
    private val appDb: AppDb,
    private val appPref: AppPreferences
) : CovidRepository {

    override suspend fun updateData(): Result<Boolean> {
        return if (!isCacheValid()) {
            getSummaryFromServer()
        } else {
            Result.Success(true)
        }
    }

    // Cache is invalid if duration exceed API_CACHE_DURATION or doesn't exist
    private fun isCacheValid(): Boolean {
        if (!appPref.contains(API_LAST_FETCH_TIMESTAMP)) return false

        val lastFetch = appPref.getLong(API_LAST_FETCH_TIMESTAMP)
        val elapsedTime = System.currentTimeMillis() - lastFetch

        return elapsedTime < API_CACHE_DURATION
    }

    private suspend fun getSummaryFromServer(): Result<Boolean> {
        return try {
            val summary = apiService.getSummary()
            saveCountriesToDb(summary.countries)
            appPref.save(API_LAST_FETCH_TIMESTAMP, System.currentTimeMillis())
            Result.Success(true)
        } catch (throwable: Throwable) {
            Result.Error(throwable)
        }
    }

    private suspend fun saveCountriesToDb(countries: List<Country>) {
        appDb.countryDao().insertAll(countries)
    }

    override suspend fun getCountryData(countryCode: String): Country? {
        return appDb.countryDao().getCountryByCode(countryCode.toUpperCase(Locale.getDefault()))
    }

    override suspend fun getGlobalData(): GlobalStatistic {
        TODO("Not yet implemented")
    }
}