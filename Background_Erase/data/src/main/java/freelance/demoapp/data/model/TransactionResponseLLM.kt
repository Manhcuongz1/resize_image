package freelance.demoapp.data.model

import freelance.demoapp.data.utils.Utils
import freelance.demoapp.domain.model.TransactionResponseFromLLM
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class TransactionResponseLLM(
    val type: String? = null,
    val dateLabel: String? = null,
    val amount: Double? = null,
    val note: String? = null,
    val category: String? = null,
) {
    companion object {
        const val INCOME = "Income"
        const val EXPENSE = "Expense"
    }
}

fun TransactionResponseLLM.toTransactionResponse(): TransactionResponseFromLLM {
    return TransactionResponseFromLLM(
        type = type ?: "",
        dateLabel = Utils.dateToLong(dateLabel, Utils.PATTERN_DATE_DEFAULT),
        amount = BigDecimal(amount ?: 0.0),
        note = note ?: "",
        category = category ?: ""
    )
}