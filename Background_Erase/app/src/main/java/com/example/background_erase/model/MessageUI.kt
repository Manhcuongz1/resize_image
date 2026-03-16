package com.example.background_erase.model

import java.math.BigDecimal


data class MessageUI(
    val id: Long = 0,

    val conversationId: Long,

    val sender: Sender,

    val content: String,

    val createdAt: Long = System.currentTimeMillis(),

    val transaction: List<TransactionUI>
) {
    sealed interface Sender {
        object User : Sender
        object LLM : Sender
    }
}
data class TransactionUI(
    val type: Type,
    val dateLabel: Long,
    val amount: BigDecimal,
    val note: String,
    val category: CategoryUI,
) {
    companion object {}

    enum class Type(val value: String) {
        EXPENSE("EXPENSE"), INCOME("INCOME")
    }
}
