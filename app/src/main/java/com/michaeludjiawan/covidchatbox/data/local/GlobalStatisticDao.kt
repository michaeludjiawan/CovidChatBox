package com.michaeludjiawan.covidchatbox.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.michaeludjiawan.covidchatbox.data.model.GlobalStatistic

@Dao
interface GlobalStatisticDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(globalStatistic: GlobalStatistic)

    @Query("SELECT * FROM GlobalStatistic LIMIT 1")
    suspend fun getStatistic(): GlobalStatistic?
}