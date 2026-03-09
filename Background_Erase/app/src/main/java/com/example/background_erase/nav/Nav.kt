package com.example.background_erase.nav

import kotlinx.serialization.Serializable

sealed class Routers {
    @Serializable
    data object Onboarding

    @Serializable
    data object ChatHomeScreen

    @Serializable
    data object Setting

    @Serializable
    data object About
}