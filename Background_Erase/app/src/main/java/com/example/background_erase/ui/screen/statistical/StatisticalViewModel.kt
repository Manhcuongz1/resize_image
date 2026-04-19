package com.example.background_erase.ui.screen.statistical

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.example.background_erase.base.BaseViewModel
import com.example.background_erase.model.CategoryGroupUI
import com.example.background_erase.model.FinanceStatisticUiState
import com.example.background_erase.model.TransactionUI
import com.example.background_erase.model.mapper.toCategoryUI
import com.example.background_erase.model.mapper.toTransactionUI
import dagger.hilt.android.lifecycle.HiltViewModel
import freelance.demoapp.domain.model.Category
import freelance.demoapp.domain.usecase.GetAnalyticUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Transaction(val title: String, val amount: String)
data class CategoryItem(
    val title: String,
    val subtitle: String,
    val amount: String,
    val iconChar: String,
    val iconBg: Color,
    val iconColor: Color,
    val transactions: List<Transaction> = emptyList()
)


@HiltViewModel
class StatisticalViewModel @Inject constructor(
    private val getAnalyticUseCase: GetAnalyticUseCase
) : BaseViewModel(){
    private val _analytic = MutableStateFlow(FinanceStatisticUiState.EMPTY)
    val analytic = _analytic.asStateFlow()


    private val _selectedTab = MutableStateFlow(TransactionUI.Type.Expense)
    val selectedTab = _selectedTab.asStateFlow()

    fun fetchStatisticData() {
        viewModelScope.launch {
            _selectedTab.collect {
                if (it == TransactionUI.Type.Expense) {
                    filterCategoryExpense()
                }
                else
                    filterCategoryIncome()
            }
        }
    }

    private suspend fun filterCategoryExpense() {
        val raw = getAnalyticUseCase.invoke()
        val transactionExpenses = raw.expenses

        val fin = FinanceStatisticUiState(
            totalBalance = transactionExpenses.sumOf {
                it.transactions.sumOf { t -> t.amount }
            },
            displayCategories = transactionExpenses.map {
                CategoryGroupUI(
                    category = it.category.toCategoryUI(),
                    transactions = it.transactions.map { t -> t.toTransactionUI() },
                    totalAmount = it.transactions.sumOf { t -> t.amount },
                    iconChar = it.category.name[0].toString(),
                    iconBg = it.category.colorBackground,
                    iconColor = Category.COLOR_ICON_DEFAULT
                )
            },
            selectedTab = TransactionUI.Type.Expense
        )
        _analytic.update { fin }
    }

    private suspend fun filterCategoryIncome(){
        val raw = getAnalyticUseCase.invoke()
        val transactionIncomes = raw.incomes

        val fin = FinanceStatisticUiState(
            totalBalance = transactionIncomes.sumOf {
                it.transactions.sumOf { t -> t.amount }
            },
            displayCategories = transactionIncomes.map {
                CategoryGroupUI(
                    category = it.category.toCategoryUI(),
                    transactions = it.transactions.map { t -> t.toTransactionUI() },
                    totalAmount = it.transactions.sumOf { t -> t.amount },
                    iconChar = it.category.name[0].toString(),
                    iconBg = it.category.colorBackground,
                    iconColor = Category.COLOR_ICON_DEFAULT
                )
            },
            selectedTab = TransactionUI.Type.Income
        )
        _analytic.update { fin }
    }

    fun onTabSelected(type: TransactionUI.Type) {
        _selectedTab.value = type
    }
}