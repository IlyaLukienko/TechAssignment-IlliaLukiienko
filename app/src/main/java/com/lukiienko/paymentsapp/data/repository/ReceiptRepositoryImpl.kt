package com.lukiienko.paymentsapp.data.repository

import com.lukiienko.paymentsapp.domain.model.Transaction
import com.lukiienko.paymentsapp.domain.repository.ReceiptRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.delay

class ReceiptRepositoryImpl(
    private val client: HttpClient
) : ReceiptRepository {
    override suspend fun fetchReceipt(): Transaction {
        delay(1000) // to show progress bar
        return client.get("https://jason-koala.wallee.workers.dev/").body()
    }
}
