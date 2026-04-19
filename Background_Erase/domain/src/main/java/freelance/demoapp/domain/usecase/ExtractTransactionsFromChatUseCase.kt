package freelance.demoapp.domain.usecase

import freelance.demoapp.domain.model.DataPrompt
import freelance.demoapp.domain.model.TransactionResponseFromLLM
import freelance.demoapp.domain.repository.LLMRepository

class ExtractTransactionsFromChatUseCase(
    private val llmRepository: LLMRepository
) {
    suspend operator fun invoke(
        dataPrompt: DataPrompt
    ) : List<TransactionResponseFromLLM>{
        return llmRepository.extractTransactions(dataPrompt)
    }
}