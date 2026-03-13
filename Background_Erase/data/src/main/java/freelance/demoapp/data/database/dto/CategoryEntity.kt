package freelance.demoapp.data.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,

    val type: Enums.TypeTransaction, // INCOME / EXPENSE

    val createdAt: Long = System.currentTimeMillis()
)