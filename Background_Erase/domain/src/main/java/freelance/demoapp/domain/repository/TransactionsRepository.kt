package freelance.demoapp.domain.repository

import freelance.demoapp.domain.model.DataPrompt
import freelance.demoapp.domain.model.TransactionResponse

interface TransactionsRepository {

    suspend fun extractTransactions(dataPrompt: DataPrompt): List<TransactionResponse>
}