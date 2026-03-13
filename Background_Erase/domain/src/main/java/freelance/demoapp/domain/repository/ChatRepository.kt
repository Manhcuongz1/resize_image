package freelance.demoapp.domain.repository

import freelance.demoapp.domain.model.Message


interface ChatRepository {

    suspend fun getChat(conversation : Long, offset : Long) : List<Message>
}