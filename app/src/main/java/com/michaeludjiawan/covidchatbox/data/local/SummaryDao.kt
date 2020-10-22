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
}