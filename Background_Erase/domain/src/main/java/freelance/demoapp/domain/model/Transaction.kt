package freelance.demoapp.domain.model

import kotlinx.serialization.Serializable


data class Transaction(
    val type: Type,
    val dateLabel: String,
    val amount: Long,
    val category: Category,
) {
    companion object {}

    enum class Type(val value: String) {
        EXPENSE("EXPENSE"), INCOME("INCOME")
    }
}