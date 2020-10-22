package com.michaeludjiawan.covidchatbox.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.michaeludjiawan.covidchatbox.data.model.CountrySummary

@Dao
interface SummaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(summary: List<CountrySummary>)

    @Query("SELECT * FROM CountrySummary")
    suspend fun getSummary(): List<CountrySummary>
}