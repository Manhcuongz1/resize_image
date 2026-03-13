package freelance.demoapp.data.database.dto.pojo

import androidx.room.Embedded
import androidx.room.Relation
import freelance.demoapp.data.database.dto.CategoryEntity
import freelance.demoapp.data.database.dto.TransactionEntity

data class TransactionWithCategoryPOJO(

    @Embedded
    val transaction: TransactionEntity,

    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: CategoryEntity
)