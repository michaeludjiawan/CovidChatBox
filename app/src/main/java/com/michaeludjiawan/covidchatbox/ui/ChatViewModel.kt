package com.michaeludjiawan.covidchatbox.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeludjiawan.covidchatbox.data.api.Result
import com.michaeludjiawan.covidchatbox.data.repository.CovidRepository
import kotlinx.coroutines.launch

class ChatViewModel(
    private val covidRepository: CovidRepository
) : ViewModel() {

    private val mutableMessageResponse = MutableLiveData<String>()
    val messageResponse: LiveData<String> = mutableMessageResponse

    private val mutableUpdateDataResult = MutableLiveData<Result<Boolean>>()
    val updateDataResult: LiveData<Result<Boolean>> = mutableUpdateDataResult

    private val mutableUpdateDataLoading = MutableLiveData<Boolean>()
    val updateDataLoading: LiveData<Boolean> = mutableUpdateDataLoading

    fun updateData() {
        mutableUpdateDataLoading.value = true

        viewModelScope.launch {
            mutableUpdateDataResult.value = covidRepository.updateData()
            mutableUpdateDataLoading.value = false
        }
    }

    fun processMessage(message: String) {
        val caseCommand = "cases"
        val deathCommand = "deaths"

        when {
            message.startsWith(caseCommand, true) -> {
                val input = message.substringAfter(caseCommand).trim()

                viewModelScope.launch {
                    val count = if (input.equals("total", true)) {
                        covidRepository.getGlobalStatistic()?.totalConfirmed
                    } else {
                        covidRepository.getCountryData(input)?.totalConfirmed
                    }

                    val responseMessage = if (count != null) {
                        "$input Active Cases $count."
                    } else {
                        "No data found."
                    }

                    mutableMessageResponse.value = responseMessage
                }
            }
            message.startsWith(deathCommand, true) -> {
                val input = message.substringAfter(deathCommand).trim()

                viewModelScope.launch {
                    val count = if (input.equals("total", true)) {
                        covidRepository.getGlobalStatistic()?.totalDeaths
                    } else {
                        covidRepository.getCountryData(input)?.totalDeaths
                    }

                    val responseMessage = if (count != null) {
                        "$input Deaths $count."
                    } else {
                        "No data found."
                    }

                    mutableMessageResponse.value = responseMessage
                }
            }
            else -> mutableMessageResponse.value = "Command is invalid."
        }
    }
}