package freelance.demoapp.domain.repository

import freelance.demoapp.domain.model.Category
import freelance.demoapp.domain.model.Transaction

interface CategoryRepository {
    suspend fun getCategories(): List<Category>

    suspend fun createNewCategory(category: Category): Long

    suspend fun getColorWhenCreateNewCategory(type: Transaction.Type): Long
}