package freelance.demoapp.data.repositoryImp

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import freelance.demoapp.data.database.database.ChatDatabase
import freelance.demoapp.data.database.mapper.toCategory
import freelance.demoapp.data.database.mapper.toCategoryEntity
import freelance.demoapp.data.database.shareprf.AppPreferences
import freelance.demoapp.data.utils.listOfColorExpense
import freelance.demoapp.data.utils.listOfColorIncome
import freelance.demoapp.domain.model.Category
import freelance.demoapp.domain.model.Transaction
import freelance.demoapp.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImp @Inject constructor(
    private val db : ChatDatabase,
    private val pref : AppPreferences,
    @ApplicationContext private val context: Context
) : CategoryRepository {
    override suspend fun getCategories(): List<Category> {
        return db.appDatabaseDao().getCategories().map { it.toCategory() }
    }

    override suspend fun createNewCategory(category: Category): Long {
        return db.appDatabaseDao().createCategory(category.toCategoryEntity())
    }

    override suspend fun getColorWhenCreateNewCategory(type: Transaction.Type): Long {
        val colors = db.appDatabaseDao().getCategories().map { it.colorBackground }
        val list =
            if (type == Transaction.Type.Expense)
                listOfColorExpense(context)
            else listOfColorIncome(context)

        return list.firstOrNull {
            !colors.contains(it)
        } ?: list.first()
    }
}