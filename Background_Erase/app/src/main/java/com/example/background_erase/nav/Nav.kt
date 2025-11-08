package com.example.background_erase.nav

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

sealed class Routers {
    @Serializable
    data object Onboarding

    @Serializable
    data object Home

    @Serializable
    data object Setting

    @Serializable
    data object About
}