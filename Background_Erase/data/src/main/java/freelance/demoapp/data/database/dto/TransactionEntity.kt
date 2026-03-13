package freelance.demoapp.data.database.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(
    tableName = "transactions",
    indices = [Index("messageId")],
    foreignKeys = [ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"],
        onDelete = ForeignKey.RESTRICT
    )]
)
data class TransactionEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val messageId: Long?,

    val categoryId: Long,

    val amount: BigDecimal,

    val description: String,

    val transactionDate: Long,

    val createdAt: Long = System.currentTimeMillis()
)