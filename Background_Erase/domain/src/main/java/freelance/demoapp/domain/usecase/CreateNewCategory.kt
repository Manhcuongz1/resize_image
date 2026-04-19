package freelance.demoapp.domain.usecase

import freelance.demoapp.domain.model.Category
import freelance.demoapp.domain.repository.CategoryRepository

class CreateNewCategory(
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(category: Category): Long {
        return categoryRepository.createNewCategory(category)
    }
}