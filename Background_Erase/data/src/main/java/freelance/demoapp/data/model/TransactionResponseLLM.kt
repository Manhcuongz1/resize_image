package freelance.demoapp.data.model

import freelance.demoapp.domain.model.TransactionResponse
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class TransactionResponseLLM(
    val type: String? = null,
    val dateLabel: Long? = null,
    val amount: Double? = null,
    val note: String? = null,
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
        dateLabel = dateLabel ?: 0L,
        amount = BigDecimal(amount ?: 0.0),
        note = note ?: "",
        category = category ?: ""
    )
}