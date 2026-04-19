package com.example.background_erase.model

import androidx.annotation.StringRes
import com.example.background_erase.R
import com.example.background_erase.base.toDefaultLocaleFormat
import java.math.BigDecimal

// 1. Model nhỏ gọn, không dư thừa
data class CategoryGroupUI(
    val category: CategoryUI,
    val transactions: List<TransactionUI>,
    val totalAmount: BigDecimal,
    val iconChar: String,
    val iconBg: Long,
    val iconColor: Long = 0xF8FAFC,
) {
    fun getSubTitle() : String {
        return transactions.size.toString() + " giao dịch"
    }
    fun getTotalAmountFormat(): String {
        return totalAmount.toDefaultLocaleFormat()
    }
}

// 2. UI State tổng thể của màn hình
data class FinanceStatisticUiState(
    val totalBalance: BigDecimal = BigDecimal.ZERO,
    val typeCurrency: String = "đ",

    val displayCategories: List<CategoryGroupUI> = emptyList(),

    val selectedTab: TransactionUI.Type,
    val isLoading: Boolean = false
) {
    companion object {
        val EMPTY get() = FinanceStatisticUiState(
            selectedTab = TransactionUI.Type.Expense
        )
    }

    @StringRes
    fun getTitleRes() : Int {
        return when (selectedTab) {
            TransactionUI.Type.Expense -> R.string.title_expense
            TransactionUI.Type.Income -> R.string.title_income
        }
    }

    fun getTotalBalanceFormat(): String {
        return if (selectedTab == TransactionUI.Type.Expense)
            totalBalance.negate().toDefaultLocaleFormat()
        else
            totalBalance.toDefaultLocaleFormat()
    }
}


