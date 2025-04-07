package com.lukiienko.paymentsapp.domain.model

data class ReceiptUiState(
    val isLoading: Boolean = false,
    val transaction: Transaction? = null,
    val finalAmount: Double? = null,
    val taxAmount: Double? = null,
    val error: String? = null
)
