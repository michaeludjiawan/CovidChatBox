package com.michaeludjiawan.covidchatbox.data.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeludjiawan.covidchatbox.data.repository.CovidRepository
import kotlinx.coroutines.launch

class ChatViewModel(
    private val covidRepository: CovidRepository
) : ViewModel() {

    private val mutableMessageResponse = MutableLiveData<String>()
    val messageResponse: LiveData<String> = mutableMessageResponse

    fun updateData() {
        viewModelScope.launch {
            covidRepository.updateData()
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
                        TODO("Not yet implemented")
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
                        TODO("Not yet implemented")
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