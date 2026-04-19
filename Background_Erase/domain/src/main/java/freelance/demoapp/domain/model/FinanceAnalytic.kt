package freelance.demoapp.domain.model

data class FinanceAnalytic(
    val expenses : List<CategoryTransaction>,
    val incomes : List<CategoryTransaction>
)

data class CategoryTransaction(
    val category: Category,
    val transactions: List<Transaction>
)