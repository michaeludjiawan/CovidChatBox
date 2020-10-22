package com.michaeludjiawan.covidchatbox.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.michaeludjiawan.covidchatbox.data.model.Country

@Dao
interface SummaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(summary: List<Country>)

    @Query("SELECT * FROM Country")
    suspend fun getSummary(): List<Country>

    @Query("SELECT totalConfirmed FROM Country WHERE countryCode = :countryCode")
    suspend fun getTotalConfirmed(countryCode: String): Int

    @Query("SELECT totalDeaths FROM Country WHERE countryCode = :countryCode")
    suspend fun getTotalDeaths(countryCode: String): Int

    @Query("SELECT * FROM Country WHERE countryCode = :countryCode")
    suspend fun getCountryByCode(countryCode: String): Country?
}