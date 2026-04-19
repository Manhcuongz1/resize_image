package com.example.background_erase.model

import java.math.BigDecimal

data class TransactionUI(
    val type: Type,
    val dateLabel: Long,
    val amount: BigDecimal,
    val note: String,
    val category: CategoryUI,
) {
    companion object {}

    enum class Type(val value: String) {
        Expense("Expense"), Income("Income")
    }
}