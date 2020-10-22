package com.michaeludjiawan.covidchatbox.data.repository

import com.michaeludjiawan.covidchatbox.data.api.Result

interface CovidRepository {
    suspend fun updateData(): Result<Boolean>
}