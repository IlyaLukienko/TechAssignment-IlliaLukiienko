package com.lukiienko.paymentsapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val transactionId: String,
    val status: String,
    val amount: Amount,
    val transactionDetails: TransactionDetails
)
