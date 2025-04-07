package com.lukiienko.paymentsapp.domain.usecase

import com.lukiienko.paymentsapp.domain.screen.IPinPadScreen

class PinPadUseCase(
    private val screen: IPinPadScreen
) {
    private var rawInput: String = ""
    private val maxLength = 8 //  99999999 → 999999.99

    fun onDigitPressed(digit: Char) {
        if (rawInput.length < maxLength) {
            rawInput += digit
            updateAmount()
        }
    }

    fun onDelete() {
        if (rawInput.isNotEmpty()) {
            rawInput = rawInput.dropLast(1)
            updateAmount()
        }
    }

    fun onClear() {
        rawInput = ""
        updateAmount()
    }

    private fun updateAmount() {
        val padded = rawInput.padStart(3, '0')
        val formatted = padded.dropLast(2) + "." + padded.takeLast(2)
        val cleaned = formatted.trimStart('0').let {
            if (it.startsWith(".")) "0$it" else it
        }
        screen.setAmount(cleaned)
    }
}

