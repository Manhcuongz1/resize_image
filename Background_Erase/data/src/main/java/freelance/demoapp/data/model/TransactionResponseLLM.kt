package freelance.demoapp.data.model

import freelance.demoapp.domain.model.Transaction
import freelance.demoapp.domain.model.TransactionResponse
import kotlinx.serialization.Serializable

@Serializable
data class TransactionResponseLLM(
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

fun TransactionResponseLLM.toTransactionResponse(): TransactionResponse {
    return TransactionResponse(
        type = type ?: "",
        dateLabel = dateLabel ?: "",
        amount = amount ?: 0,
        category = category ?: ""
    )
}