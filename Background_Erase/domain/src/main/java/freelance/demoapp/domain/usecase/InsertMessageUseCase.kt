package freelance.demoapp.domain.usecase

import freelance.demoapp.domain.model.Message
import freelance.demoapp.domain.repository.ChatRepository

class InsertMessageUseCase (
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(message: Message) : Long{
        return chatRepository.insertMessage(message)
    }

}