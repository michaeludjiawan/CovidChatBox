package com.michaeludjiawan.covidchatbox.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.michaeludjiawan.covidchatbox.data.model.CountrySummary

@Database(
    entities = [CountrySummary::class],
    version = 1
)
@TypeConverters(value = [DateTypeConverter::class])
abstract class AppDb: RoomDatabase() {
    abstract fun summaryDao(): SummaryDao
}