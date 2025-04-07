package com.lukiienko.paymentsapp.domain.screen

import com.lukiienko.paymentsapp.domain.model.ReceiptUiState

interface IReceiptScreen {
    fun updateState(state: ReceiptUiState)
}