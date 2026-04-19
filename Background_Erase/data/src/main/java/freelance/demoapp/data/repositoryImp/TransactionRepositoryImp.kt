package freelance.demoapp.data.repositoryImp

import freelance.demoapp.data.database.database.ChatDatabase
import freelance.demoapp.data.database.dto.Enums
import freelance.demoapp.data.database.dto.pojo.TransactionWithCategoryPOJO
import freelance.demoapp.data.database.mapper.toCategory
import freelance.demoapp.data.database.mapper.toTransaction
import freelance.demoapp.data.database.shareprf.AppPreferences
import freelance.demoapp.domain.model.CategoryTransaction
import freelance.demoapp.domain.model.FinanceAnalytic
import freelance.demoapp.domain.model.Transaction
import freelance.demoapp.domain.repository.TransactionRepository
import javax.inject.Inject

class TransactionRepositoryImp @Inject constructor(
    private val db : ChatDatabase,
    private val pref : AppPreferences
) : TransactionRepository {
    override suspend fun getAllTransactions(): FinanceAnalytic {
        val list = db.appDatabaseDao().getAllTransaction()
        val (incomes, expenses) = list.partition { it.category.type == Enums.TypeTransaction.Income }

        return FinanceAnalytic(
            expenses = mapToCategoryTransaction(expenses),
            incomes = mapToCategoryTransaction(incomes)
        )
    }

    private fun mapToCategoryTransaction(pojos: List<TransactionWithCategoryPOJO>): List<CategoryTransaction> {
        return pojos.groupBy { it.category.id }
            .map { (_, items) ->
                val category = items.first().category.toCategory()
                CategoryTransaction(
                    category = category,
                    transactions = items.map { it.transaction.toTransaction(category) }
                )
            }
            .sortedByDescending { it.transactions.sumOf { tx -> tx.amount } }
    }

    override suspend fun getExpenseTransactions(): List<Transaction> {
        TODO("Not yet implemented")
    }

}