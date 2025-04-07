package com.lukiienko.paymentsapp.domain.usecase

import com.lukiienko.paymentsapp.domain.model.Amount
import com.lukiienko.paymentsapp.domain.model.Transaction
import com.lukiienko.paymentsapp.domain.model.TransactionDetails
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class ReceiptUseCaseTest {

    private lateinit var useCase: ReceiptUseCase

    @Before
    fun setUp() {
        useCase = ReceiptUseCase(
            screen = mockk(relaxed = true),
            repository = mockk(),
            stringHelper = mockk()
        )
    }

    @Test
    fun `WHEN tax is calculated THEN returns taxableAmount times taxRate`() {
        val transaction = Transaction(
            transactionId = "tx123",
            status = "Success",
            amount = Amount(
                purchaseAmount = "100.00",
                currency = "USD",
                taxableAmount = "90.00",
                taxRate = "0.10",
                tipAmount = "0.00",
                discountAmount = "0.00"
            ),
            transactionDetails = TransactionDetails("2025-01-24T12:30:00Z")
        )

        val result = useCase.calculateTax(transaction)
        assertEquals(9.0, result, 0.001)
    }

    @Test
    fun `WHEN final amount is calculated THEN applies tax tip discount correctly`() {
        val transaction = Transaction(
            transactionId = "tx456",
            status = "Success",
            amount = Amount(
                purchaseAmount = "100.00",
                currency = "USD",
                taxableAmount = "90.00",
                taxRate = "0.10",
                tipAmount = "10.00",
                discountAmount = "5.00"
            ),
            transactionDetails = TransactionDetails("2025-01-24T12:30:00Z")
        )

        val result = useCase.calculateFinalAmount(transaction)
        assertEquals(114.0, result, 0.001)
    }

    @Test
    fun `WHEN fields are empty THEN calculateFinalAmount returns zero`() {
        val transaction = Transaction(
            transactionId = "tx789",
            status = "Success",
            amount = Amount(
                purchaseAmount = "",
                currency = "USD",
                taxableAmount = "",
                taxRate = "",
                tipAmount = "",
                discountAmount = ""
            ),
            transactionDetails = TransactionDetails("2025-01-24T12:30:00Z")
        )

        val result = useCase.calculateFinalAmount(transaction)
        assertEquals(0.0, result, 0.001)
    }
}
