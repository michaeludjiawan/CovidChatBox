package com.michaeludjiawan.covidchatbox.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.michaeludjiawan.covidchatbox.data.model.Country
import com.michaeludjiawan.covidchatbox.data.model.GlobalStatistic

@Database(
    entities = [Country::class, GlobalStatistic::class],
    version = 1
)
@TypeConverters(value = [DateTypeConverter::class])
abstract class AppDb: RoomDatabase() {
    abstract fun countryDao(): CountryDao
    abstract fun globalStatisticDao(): GlobalStatisticDao
}