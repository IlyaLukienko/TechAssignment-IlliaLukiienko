package com.lukiienko.paymentsapp.core.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = OkButtonColor,
    background = BackgroundColor,
    surface = DisplayColor,
    onSurface = TextColor,
    onPrimary = Color.White
)

@Composable
fun PaymentsAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}