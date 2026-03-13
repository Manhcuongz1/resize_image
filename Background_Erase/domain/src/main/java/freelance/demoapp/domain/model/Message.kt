package freelance.demoapp.domain.model

data class Message(
    val id: Long = 0,

    val conversationId: Long,

    val sender: Sender,

    val content: String,

    val createdAt: Long = System.currentTimeMillis(),

    val transaction: List<Transaction>
) {
    enum class Sender {
        User, LLM
    }
}