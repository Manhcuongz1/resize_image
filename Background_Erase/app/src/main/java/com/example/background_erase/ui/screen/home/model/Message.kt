package com.example.background_erase.ui.screen.home.model


enum class TransactionTypeUI { EXPENSE, INCOME }
data class Message(
    val sender: Sender = Sender.User(),
    val time: String? = null
) {
    fun isUser() = sender is Sender.User
    sealed class Sender {
        data class LLM(
            val aiModel: String = "",
            val payLoad: BotPayLoad
        ) : Sender()
        data class User(
            val text: String = "",
        ) : Sender()
    }
}
sealed class BotPayLoad

data class TextDataUI(
    val text: String
) : BotPayLoad()

data class TransactionListUI(
    val transactions: List<TransactionUI>
) : BotPayLoad()

data class TransactionUI(
    val type: TransactionTypeUI,
    val dateLabel: String,
    val amount: Long,
    val category: String,
    val note: String
)