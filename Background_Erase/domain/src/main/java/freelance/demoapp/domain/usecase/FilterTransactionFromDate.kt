package freelance.demoapp.domain.usecase

import freelance.demoapp.domain.model.CategoryTransaction
import java.time.YearMonth
import java.time.ZoneId

class FilterTransactionFromDate {

    suspend operator fun invoke(
        categoriesTransaction: List<CategoryTransaction>,
        yearMonth: YearMonth
    ): List<CategoryTransaction> {
        val zoneId = ZoneId.systemDefault()

        val startOfMonthMillis = yearMonth.atDay(1)
            .atStartOfDay(zoneId)
            .toInstant()
            .toEpochMilli()

        val endOfMonthMillis = yearMonth.plusMonths(1).atDay(1)
            .atStartOfDay(zoneId)
            .toInstant()
            .toEpochMilli() - 1L

        return categoriesTransaction.mapNotNull { categoryGroup ->

            val validTransactions = categoryGroup.transactions.filter { tx ->
                tx.dateLabel in startOfMonthMillis..endOfMonthMillis
            }

            if (validTransactions.isEmpty()) {
                null
            } else {
                categoryGroup.copy(transactions = validTransactions)
            }
        }
    }
}