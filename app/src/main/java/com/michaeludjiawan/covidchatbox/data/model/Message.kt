package com.michaeludjiawan.covidchatbox.data.model

data class Message(
    val message: String,
    val senderType: SenderType
)

enum class SenderType { SENT, RECEIVED }