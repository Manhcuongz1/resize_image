package freelance.demoapp.domain.usecase

import freelance.demoapp.domain.model.FinanceAnalytic
import freelance.demoapp.domain.repository.TransactionRepository

class GetAnalyticUseCase(
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke() : FinanceAnalytic {
        val trans = transactionRepository.getAllTransactions()
        return trans
    }
}