package com.example.background_erase.model


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
