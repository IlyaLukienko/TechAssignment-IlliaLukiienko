@file:OptIn(ExperimentalMaterial3Api::class)

package com.lukiienko.paymentsapp.presentation.screen.pinpad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lukiienko.paymentsapp.presentation.navigation.Routes
import com.lukiienko.paymentsapp.presentation.viewmodel.PinPadViewModel

@Composable
fun PinPadScreen(
    navController: NavController,
    viewModel: PinPadViewModel = hiltViewModel()
) {
    val amount by viewModel.amount.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF9FC))
            .safeDrawingPadding()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Purchase",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Please enter amount.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp, bottom = 32.dp)
            )

            PinPadDisplay(amount)
        }

        Spacer(modifier = Modifier.height(32.dp))

        PinPadKeypad(
            onDigit = { viewModel.onDigitPressed(it) },
            onClear = { viewModel.onClear() },
            onDelete = { viewModel.onDelete() }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { navController.navigate(Routes.RECEIPT) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4DA6A7),
                contentColor = Color.White
            )
        ) {
            Text("OK")
        }
    }
}
