package com.michaeludjiawan.covidchatbox.di

import android.content.Context
import androidx.room.Room
import com.michaeludjiawan.covidchatbox.data.local.AppDb
import com.michaeludjiawan.covidchatbox.data.local.AppPreferences
import org.koin.dsl.module

val dataModule = module {
    single { createDatabase(get()) }
    single { createSharedPref(get()) }

    single { (get() as AppDb).countryDao() }
}

fun createSharedPref(context: Context): AppPreferences {
    return AppPreferences.getInstance(context)
}

fun createDatabase(context: Context): AppDb {
    return Room.databaseBuilder(context, AppDb::class.java, "app_db")
        .fallbackToDestructiveMigration()
        .build()
}