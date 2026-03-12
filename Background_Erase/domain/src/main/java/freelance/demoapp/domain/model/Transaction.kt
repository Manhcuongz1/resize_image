package freelance.demoapp.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Transaction(
    val type: Type,
    val dateLabel: String,
    val amount: Long,
    val category: String,
) {
    companion object{}

    enum class Type(val value: String) {
        EXPENSE("EXPENSE"), INCOME("INCOME")
    }
}