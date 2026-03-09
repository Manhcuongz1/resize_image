package com.example.background_erase.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.background_erase.ui.resource.White_0xFFEAE5DE

private val bg = White_0xFFEAE5DE

@Suppress("FunctionName")
fun Modifier.WrapDefault(paddingHorizontal: Dp = 16.dp, paddingVertical : Dp =  16.dp): Modifier {
    return this
        .fillMaxSize()

        .systemBarsPadding()
        .padding(paddingHorizontal, paddingVertical)
}