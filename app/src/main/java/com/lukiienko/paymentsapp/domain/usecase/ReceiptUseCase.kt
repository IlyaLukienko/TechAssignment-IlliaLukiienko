package com.lukiienko.paymentsapp.domain.usecase

import com.lukiienko.paymentsapp.R
import com.lukiienko.paymentsapp.core.util.StringResourcesHelper
import com.lukiienko.paymentsapp.domain.model.ReceiptUiState
import com.lukiienko.paymentsapp.domain.model.Transaction
import com.lukiienko.paymentsapp.domain.repository.ReceiptRepository
import com.lukiienko.paymentsapp.domain.screen.IReceiptScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReceiptUseCase(
    private val screen: IReceiptScreen,
    private val repository: ReceiptRepository,
    private val stringHelper: StringResourcesHelper
) {

    fun loadReceipt() {
        screen.updateState(ReceiptUiState(isLoading = true))

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val transaction = repository.fetchReceipt()
                val finalAmount = calculateFinalAmount(transaction)
                val taxAmount = calculateTax(transaction)

                withContext(Dispatchers.Main) {
                    screen.updateState(
                        ReceiptUiState(
                            isLoading = false,
                            transaction = transaction,
                            finalAmount = finalAmount,
                            taxAmount = taxAmount,
                            error = null
                        )
                    )
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    screen.updateState(
                        ReceiptUiState(
                            isLoading = false,
                            transaction = null,
                            finalAmount = null,
                            taxAmount = null,
                            error = e.message ?: stringHelper.get(R.string.unknown_error)
                        )
                    )
                }
            }
        }
    }

    internal fun calculateFinalAmount(transaction: Transaction): Double {
        val amount = transaction.amount
        val purchase = amount.purchaseAmount.toDoubleOrNull() ?: 0.0
        val taxable = amount.taxableAmount.toDoubleOrNull() ?: 0.0
        val rate = amount.taxRate.toDoubleOrNull() ?: 0.0
        val tip = amount.tipAmount.toDoubleOrNull() ?: 0.0
        val discount = amount.discountAmount.toDoubleOrNull() ?: 0.0

        return purchase + (taxable * rate) + tip - discount
    }

    internal fun calculateTax(transaction: Transaction): Double {
        val taxable = transaction.amount.taxableAmount.toDoubleOrNull() ?: 0.0
        val rate = transaction.amount.taxRate.toDoubleOrNull() ?: 0.0
        return taxable * rate
    }
}

