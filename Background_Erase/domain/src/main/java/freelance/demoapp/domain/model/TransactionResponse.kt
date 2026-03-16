package freelance.demoapp.domain.model

import java.math.BigDecimal

data class TransactionResponse(
    val type: String,
    val dateLabel: Long,
    val amount: BigDecimal,
    val note: String,
    val category: String,
) {
}
fun TransactionResponse.toTransaction(): Transaction? {
    val typeTransaction = when(this.type) {
        Transaction.Type.EXPENSE.value -> Transaction.Type.EXPENSE
        Transaction.Type.INCOME.value -> Transaction.Type.INCOME
        else -> return null
    }
    return Transaction(
        type = typeTransaction,
        dateLabel = dateLabel,
        amount = amount,
        note = note,
        category = Category(
            name = this@toTransaction.category,
            type = typeTransaction
        )
    )
}