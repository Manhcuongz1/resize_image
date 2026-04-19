package freelance.demoapp.domain.model

import java.math.BigDecimal

data class TransactionResponseFromLLM(
    val type: String,
    val dateLabel: Long,
    val amount: BigDecimal,
    val note: String,
    val category: String,
) {
}
fun TransactionResponseFromLLM.toTransaction(category: Category?): Transaction? {
    if (category == null) return null
    val typeTransaction = when(this.type) {
        Transaction.Type.Expense.value -> Transaction.Type.Expense
        Transaction.Type.Income.value -> Transaction.Type.Income
        else -> return null
    }
    return Transaction(
        type = typeTransaction,
        dateLabel = dateLabel,
        amount = amount,
        note = note,
        category = category
    )
}