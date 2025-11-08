package com.example.background_erase.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.background_erase.ui.resource.Black_0xFF0F0F10

private val bg = Black_0xFF0F0F10

@Suppress("FunctionName")
fun Modifier.WrapDefault(): Modifier {
    return this
        .fillMaxSize()
        .background(bg)
        .systemBarsPadding()
        .padding(16.dp, 16.dp)
}