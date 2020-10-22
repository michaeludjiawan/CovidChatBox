package com.michaeludjiawan.covidchatbox.di

import com.michaeludjiawan.covidchatbox.data.repository.CovidRepository
import com.michaeludjiawan.covidchatbox.data.repository.CovidRepositoryImpl
import com.michaeludjiawan.covidchatbox.data.ui.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel { ChatViewModel(get()) }

    single<CovidRepository> { CovidRepositoryImpl(get(), get(), get()) }
}