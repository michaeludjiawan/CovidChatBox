package com.michaeludjiawan.covidchatbox.di

import com.michaeludjiawan.covidchatbox.data.repository.CovidRepository
import com.michaeludjiawan.covidchatbox.data.repository.CovidRepositoryImpl
import org.koin.dsl.module

val featureModule = module {
    single<CovidRepository> { CovidRepositoryImpl(get(), get(), get()) }
}