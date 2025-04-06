package com.lukiienko.paymentsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.lukiienko.paymentsapp.domain.screen.IPinPadScreen
import com.lukiienko.paymentsapp.domain.usecase.PinPadUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PinPadViewModel @Inject constructor() : ViewModel(), IPinPadScreen {

    private val _amount = MutableStateFlow("0.00")
    val amount: StateFlow<String> = _amount

    private val useCase = PinPadUseCase(this)

    fun onDigitPressed(digit: Char) = useCase.onDigitPressed(digit)
    fun onDelete() = useCase.onDelete()
    fun onClear() = useCase.onClear()

    override fun setAmount(amount: String) {
        _amount.value = amount
    }
}
