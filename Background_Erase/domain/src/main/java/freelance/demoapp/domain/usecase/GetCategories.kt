package freelance.demoapp.domain.usecase

import freelance.demoapp.domain.model.Category
import freelance.demoapp.domain.repository.CategoryRepository

class GetCategories(
    private val categoryRepository: CategoryRepository
) {
    suspend fun invoke() : List<Category> {
        return categoryRepository.getCategories()
    }
}