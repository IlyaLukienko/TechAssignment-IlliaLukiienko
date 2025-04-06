package com.lukiienko.paymentsapp.domain.usecase

import com.lukiienko.paymentsapp.domain.screen.IPinPadScreen
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class PinPadUseCaseTest {

    @MockK(relaxed = true)
    lateinit var screen: IPinPadScreen

    private lateinit var useCase: PinPadUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = PinPadUseCase(screen)
        clearMocks(screen)
    }

    @Test
    fun whenDigitsAreAdded_thenFormattedCorrectly() {
        useCase.onDigitPressed('1')
        verify { screen.setAmount("0.01") }

        useCase.onDigitPressed('2')
        verify { screen.setAmount("0.12") }

        useCase.onDigitPressed('3')
        verify { screen.setAmount("1.23") }
    }

    @Test
    fun whenClearPressed_thenAmountResetsToZero() {
        useCase.onDigitPressed('5')
        useCase.onDigitPressed('6')
        useCase.onClear()
        verify { screen.setAmount("0.00") }
    }

    @Test
    fun whenDeletePressed_thenLastDigitRemoved() {
        useCase.onDigitPressed('7')
        useCase.onDigitPressed('8')
        useCase.onDigitPressed('9')
        useCase.onDelete()
        verify { screen.setAmount("0.78") }

        useCase.onDelete()
        verify { screen.setAmount("0.07") }
    }

    @Test
    fun whenTooManyDigitsEntered_thenInputIsIgnored() {
        repeat(10) { useCase.onDigitPressed('9') }
        verify { screen.setAmount("999999.99") }

        useCase.onDigitPressed('1') // should be ignored
        verify(exactly = 1) { screen.setAmount("999999.99") }
    }
}

