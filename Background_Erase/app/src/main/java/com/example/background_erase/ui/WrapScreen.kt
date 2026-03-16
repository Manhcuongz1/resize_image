package com.example.background_erase.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.background_erase.ui.resource.BaseBackground

@Suppress("FunctionName")
fun Modifier.WrapDefault(paddingHorizontal: Dp = 8.dp, paddingVertical: Dp = 8.dp): Modifier {
    return this
        .fillMaxSize()
        .background(BaseBackground)
        .systemBarsPadding()
        .padding(paddingHorizontal, paddingVertical)
}