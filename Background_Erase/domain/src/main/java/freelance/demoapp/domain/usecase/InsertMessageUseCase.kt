package freelance.demoapp.domain.usecase

import freelance.demoapp.domain.model.Message
import freelance.demoapp.domain.repository.ChatRepository

/**
 * UseCase này khi insert Message, nếu trong message có chứa
 * thông tin giao dịch, nó sẽ insert cả giao dịch đó vào database
 */
class InsertMessageUseCase (
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(message: Message) : Long{
        return chatRepository.insertMessage(message)
    }

}