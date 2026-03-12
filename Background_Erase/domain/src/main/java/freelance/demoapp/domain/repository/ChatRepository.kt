package freelance.demoapp.domain.repository

import freelance.demoapp.domain.model.DataPrompt
import freelance.demoapp.domain.model.Transaction

interface ChatRepository {

    suspend fun generateTransaction(dataPrompt: DataPrompt) : Transaction
}