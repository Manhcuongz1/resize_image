package freelance.demoapp.data.repositoryImp

import freelance.demoapp.data.database.database.ChatDatabase
import freelance.demoapp.data.database.dto.pojo.MessageTransactionCategoryPOJO
import freelance.demoapp.data.database.mapper.toMessageEntity
import freelance.demoapp.data.database.mapper.toTransactionEntity
import freelance.demoapp.domain.model.Message
import freelance.demoapp.domain.repository.ChatRepository
import javax.inject.Inject

class ChatRepositoryImp @Inject constructor(
    private val db : ChatDatabase
) : ChatRepository {
    override suspend fun getChat(conversation: Long, offset: Long): List<Message> {
        val raw = db.appDatabaseDao().getMessagesWithTransactionCategory(conversation, offset)
        return mapper(raw)
    }

    override suspend fun insertMessage(message: Message): Long {
        val id = db.appDatabaseDao().insertMessageWithTransactions(
            message.toMessageEntity(),
            message.transaction.map {
                it.toTransactionEntity(message.id)
            }
        )
        return id
    }

    private fun mapper(raw: List<MessageTransactionCategoryPOJO>) : List<Message> {
        TODO()
    }

}