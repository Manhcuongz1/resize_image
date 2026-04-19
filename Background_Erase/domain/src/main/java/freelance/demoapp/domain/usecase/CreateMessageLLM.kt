package freelance.demoapp.domain.usecase

import freelance.demoapp.domain.model.Category
import freelance.demoapp.domain.model.Message
import freelance.demoapp.domain.model.Transaction
import freelance.demoapp.domain.model.TransactionResponseFromLLM
import freelance.demoapp.domain.model.toTransaction
import freelance.demoapp.domain.repository.CategoryRepository

class CreateMessageLLM(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(
        transactionsLLM: List<TransactionResponseFromLLM>,
    ): Message {
        val currentCategories = categoryRepository.getCategories()
        val resolvedCategories = ensureCategoriesExist(transactionsLLM, currentCategories)

        // Bước 3: Mapping sang Transaction model và đóng gói Message
        val saveTransactions = transactionsLLM.mapNotNull { item ->
            val category = resolvedCategories.find { it.name == item.category }
            item.toTransaction(category)
        }

        return Message(
            id = -1,
            conversationId = 1,
            sender = Message.Sender.LLM,
            content = "Đã nhập các thu chi sau:",
            transaction = saveTransactions
        )
    }

    private suspend fun ensureCategoriesExist(
        llmItems: List<TransactionResponseFromLLM>,
        existingList: List<Category>
    ): List<Category> {
        val resultList = existingList.toMutableList()

        val uniqueCategoryPairsFromLLM = llmItems
            .map { it.category to Transaction.Type.of(it.type) }
            .distinct()

        uniqueCategoryPairsFromLLM.forEach { (name, type) ->
            val isExisted = existingList.any { it.name == name && it.type == type }

            if (!isExisted) {
                val newCat = Category(
                    id = 0,
                    name = name,
                    colorBackground = categoryRepository.getColorWhenCreateNewCategory(type),
                    type = type
                )
                val savedId = categoryRepository.createNewCategory(newCat)
                resultList.add(newCat.copy(id = savedId))
            }
        }

        return resultList
    }
}