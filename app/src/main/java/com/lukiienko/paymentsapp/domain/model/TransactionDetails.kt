package com.lukiienko.paymentsapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TransactionDetails(
    val timestamp: String
)
