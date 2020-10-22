package com.michaeludjiawan.covidchatbox

import android.app.Application
import com.michaeludjiawan.covidchatbox.di.dataModule
import com.michaeludjiawan.covidchatbox.di.featureModule
import com.michaeludjiawan.covidchatbox.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(networkModule, dataModule, featureModule))
        }
    }
}