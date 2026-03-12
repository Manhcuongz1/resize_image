package freelance.demoapp.domain.usecase

import freelance.demoapp.domain.Message
import freelance.demoapp.domain.repository.ChatRepository

class GetChatUserCase(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke (
        conversationId: String,
        limit: Int,
        beforeMessageId: String? = null
    ) : List<Message>{
       TODO()
    }
}