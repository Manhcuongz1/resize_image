package com.example.background_erase.model


data class CategoryUI(
    val id: Long = 0,

    val name: String,

    val colorBackground: Long,

    val type: TransactionUI.Type
)