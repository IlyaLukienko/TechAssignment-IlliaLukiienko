package com.lukiienko.paymentsapp.presentation.ui.pinpad

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PinPadKeypad(
    onDigit: (Char) -> Unit,
    onDelete: () -> Unit,
    onClear: () -> Unit
) {
    val buttons = listOf(
        '1', '2', '3',
        '4', '5', '6',
        '7', '8', '9',
        'C', '0', '⌫'
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .fillMaxWidth()
    ) {
        for (row in buttons.chunked(3)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                row.forEach { char ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFE3E4E8))
                            .clickable {
                                when (char) {
                                    'C' -> onClear()
                                    '⌫' -> onDelete()
                                    else -> onDigit(char)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char.toString(),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }
    }
}
