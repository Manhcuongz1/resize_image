package freelance.demoapp.domain.usecase

import freelance.demoapp.domain.model.DataPrompt
import freelance.demoapp.domain.model.TransactionResponse
import freelance.demoapp.domain.repository.TransactionsRepository

class ExtractTransactionsFromChatUseCase(
    private val transactionsRepository: TransactionsRepository
) {
    suspend operator fun invoke(
        dataPrompt: DataPrompt
    ) : List<TransactionResponse>{
        return transactionsRepository.extractTransactions(dataPrompt)
    }
}