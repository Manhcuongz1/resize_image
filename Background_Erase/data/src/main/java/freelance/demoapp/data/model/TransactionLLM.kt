package freelance.demoapp.data.model

import freelance.demoapp.domain.model.Transaction
import kotlinx.serialization.Serializable

@Serializable
data class TransactionLLM(
    val type: String? = null,
    val dateLabel: String? = null,
    val amount: Long? = null,
    val category: String? = null,
) {
    companion object {
        const val INCOME = "INCOME"
        const val EXPENSE = "EXPENSE"
    }
}

fun TransactionLLM.toTransaction(): Transaction {
    return Transaction(
        type = if (type == TransactionLLM.INCOME) Transaction.Type.INCOME else Transaction.Type.EXPENSE,
        dateLabel = dateLabel ?: "",
        amount = amount ?: 0,
        category = category ?: ""
    )
}