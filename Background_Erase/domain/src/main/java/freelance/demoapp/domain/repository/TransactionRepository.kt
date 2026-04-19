package freelance.demoapp.domain.repository

import freelance.demoapp.domain.model.FinanceAnalytic
import freelance.demoapp.domain.model.Transaction

interface TransactionRepository {

    suspend fun getAllTransactions(): FinanceAnalytic

    suspend fun getExpenseTransactions(): List<Transaction>

}