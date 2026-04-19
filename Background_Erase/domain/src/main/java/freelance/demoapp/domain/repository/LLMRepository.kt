package freelance.demoapp.domain.repository

import freelance.demoapp.domain.model.DataPrompt
import freelance.demoapp.domain.model.TransactionResponseFromLLM

interface LLMRepository {

    suspend fun extractTransactions(dataPrompt: DataPrompt): List<TransactionResponseFromLLM>
}