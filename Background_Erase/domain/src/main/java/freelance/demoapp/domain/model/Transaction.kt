package freelance.demoapp.domain.model

import java.math.BigDecimal


data class Transaction(
    val type: Type,
    val dateLabel: Long,
    val amount: BigDecimal,
    val note: String,
    val category: Category,
) {
    companion object {}

    enum class Type(val value: String) {
        EXPENSE("EXPENSE"), INCOME("INCOME")
    }
}