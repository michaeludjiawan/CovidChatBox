package com.michaeludjiawan.covidchatbox.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.michaeludjiawan.covidchatbox.data.model.Country

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<Country>)

    @Query("SELECT * FROM Country")
    suspend fun getCountries(): List<Country>

    @Query("SELECT * FROM Country WHERE countryCode = :countryCode")
    suspend fun getCountryByCode(countryCode: String): Country?
}