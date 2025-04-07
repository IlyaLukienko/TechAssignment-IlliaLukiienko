package com.lukiienko.paymentsapp.domain.repository

import com.lukiienko.paymentsapp.domain.model.Transaction

interface ReceiptRepository {
    suspend fun fetchReceipt(): Transaction
}