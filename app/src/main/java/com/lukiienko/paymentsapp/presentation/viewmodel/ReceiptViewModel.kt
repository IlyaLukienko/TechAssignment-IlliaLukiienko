package com.lukiienko.paymentsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.lukiienko.paymentsapp.core.util.StringResourcesHelper
import com.lukiienko.paymentsapp.domain.model.ReceiptUiState
import com.lukiienko.paymentsapp.domain.repository.ReceiptRepository
import com.lukiienko.paymentsapp.domain.screen.IReceiptScreen
import com.lukiienko.paymentsapp.domain.usecase.ReceiptUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ReceiptViewModel @Inject constructor(
    receiptRepository: ReceiptRepository,
    stringResourcesHelper: StringResourcesHelper
) : ViewModel(), IReceiptScreen {
    private val useCase = ReceiptUseCase(this, receiptRepository, stringResourcesHelper)

    private val _state = MutableStateFlow(ReceiptUiState())
    val state: StateFlow<ReceiptUiState> = _state

    init {
        useCase.loadReceipt()
    }

    override fun updateState(state: ReceiptUiState) {
        _state.value = state
    }
}