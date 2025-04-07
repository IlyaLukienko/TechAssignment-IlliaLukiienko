package com.lukiienko.paymentsapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Amount(
    val purchaseAmount: String,
    val currency: String,
    val taxableAmount: String,
    val taxRate: String,
    val tipAmount: String,
    val discountAmount: String
)
