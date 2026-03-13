package freelance.demoapp.data.repositoryImp

import freelance.demoapp.data.database.database.ChatDatabase
import freelance.demoapp.data.database.dto.pojo.MessageTransactionCategoryPOJO
import freelance.demoapp.domain.model.Message
import freelance.demoapp.domain.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImp @Inject constructor(
    private val db : ChatDatabase
) : ChatRepository {
    override suspend fun getChat(conversation: Long, offset: Long): List<Message> {
        val raw = db.chatDao().getMessagesWithTransactionCategory(conversation, offset)
        return mapper(raw)
    }

    private fun mapper(raw: List<MessageTransactionCategoryPOJO>) : List<Message> {
        TODO()
    }

}