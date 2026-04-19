package com.example.background_erase.model

import androidx.compose.ui.graphics.Color
import java.math.BigDecimal

data class FinanceAnalyticUI(
    val totalIncome: BigDecimal,
    val totalExpense: BigDecimal,
    val totalBalance: BigDecimal,
    val typeCurrency: String,
    val expenses : List<CategoryTransactionUI>,
    val incomes : List<CategoryTransactionUI>
) {
    companion object {
        val EMPTY = FinanceAnalyticUI(
            totalIncome = BigDecimal.ZERO,
            totalExpense = BigDecimal.ZERO,
            totalBalance = BigDecimal.ZERO,
            typeCurrency = "",
            expenses = emptyList(),
            incomes = emptyList()
        )
    }
}

data class CategoryTransactionUI(
    val iconChar: String,
    val color: Color,
    val category: CategoryUI,
    val transactions: List<TransactionUI>
)
