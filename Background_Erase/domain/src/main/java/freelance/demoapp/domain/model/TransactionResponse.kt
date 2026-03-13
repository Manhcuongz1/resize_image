package freelance.demoapp.domain.model

data class TransactionResponse(
    val type: String,
    val dateLabel: String,
    val amount: Long,
    val category: String,
) {
}