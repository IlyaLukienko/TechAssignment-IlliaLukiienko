package com.lukiienko.paymentsapp.presentation.ui.receipt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lukiienko.paymentsapp.R
import com.lukiienko.paymentsapp.core.util.StringResourcesHelper
import com.lukiienko.paymentsapp.presentation.viewmodel.ReceiptViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReceiptScreen(
    navController: NavController,
    viewModel: ReceiptViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val stringHelper = remember { StringResourcesHelper(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                state.error != null -> {
                    Text(
                        text = state.error ?: "",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                state.transaction != null -> {
                    val tx = state.transaction ?: return@Box
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        ReceiptRow(
                            label = stringHelper.get(R.string.transaction_id),
                            value = tx.transactionId
                        )
                        ReceiptRow(
                            label = stringHelper.get(R.string.transaction_status),
                            value = tx.status
                        )
                        ReceiptRow(
                            label = stringHelper.get(R.string.final_amount),
                            value = "${state.finalAmount}"
                        )
                        ReceiptRow(
                            label = stringHelper.get(R.string.tax_amount),
                            value = state.taxAmount.toString()
                        )
                        ReceiptRow(
                            label = stringHelper.get(R.string.date_time),
                            value = tx.transactionDetails.timestamp
                        )
                    }
                }
            }
        }
    }
}
