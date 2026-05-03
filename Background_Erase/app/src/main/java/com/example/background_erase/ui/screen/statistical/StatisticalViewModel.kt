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
import freelance.demoapp.domain.model.CategoryTransaction
import freelance.demoapp.domain.usecase.FilterTransactionFromDate
import freelance.demoapp.domain.usecase.GetAnalyticUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Month
import java.time.YearMonth
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
    private val getAnalyticUseCase: GetAnalyticUseCase,
    private val filterTransactionFromDate: FilterTransactionFromDate
) : BaseViewModel(){
    private val _analytic = MutableStateFlow(FinanceStatisticUiState.EMPTY)
    val analytic = _analytic.asStateFlow()

    private val _currentTime = MutableStateFlow(YearMonth.now())
    val currentTime = _currentTime.asStateFlow()

    private val _selectedTab = MutableStateFlow(TransactionUI.Type.Expense)
    val selectedTab = _selectedTab.asStateFlow()

    init {
        fetchStatisticData()
    }
    fun fetchStatisticData() {
        viewModelScope.launch {
            combine(
                _selectedTab,
                currentTime,
            ) { tab, time ->
                tab to time
            }.collect { (tab, time) ->
                getAndFilterTransaction(tab, time)
            }
        }
    }

    private suspend fun getAndFilterTransaction(tab: TransactionUI.Type, time: YearMonth) {
        val raw = getAnalyticUseCase.invoke()
        if (tab == TransactionUI.Type.Expense) {
            val transactionExpenses = raw.expenses
            val filtered = filterTransactionFromDate.invoke(transactionExpenses, time)
            filterCategoryExpense(filtered)
        }
        else {
            val transactionIncomes = raw.incomes
            val filtered = filterTransactionFromDate.invoke(transactionIncomes, time)
            filterCategoryIncome(filtered)
        }

    }

    private suspend fun filterCategoryExpense(transactionExpenses : List<CategoryTransaction> ) {
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

    private suspend fun filterCategoryIncome(transactionIncomes : List<CategoryTransaction>){
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

    fun onPreviousMonth() {
        _currentTime.update {
            YearMonth.of(
                if (it.monthValue == 1) it.year - 1 else it.year,
                if (it.monthValue == 1) Month.DECEMBER.value else (it.monthValue - 1)
            )
        }
    }
    fun onNextMonth() {
        _currentTime.update {
            YearMonth.of(
                if (it.monthValue == 12) it.year + 1 else it.year,
                if (it.monthValue == 12) Month.JANUARY.value else it.monthValue + 1
            )
        }
    }
}