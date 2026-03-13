package freelance.demoapp.data.database.dto.pojo

import androidx.room.Embedded
import freelance.demoapp.data.database.dto.CategoryEntity
import freelance.demoapp.data.database.dto.MessageEntity
import freelance.demoapp.data.database.dto.TransactionEntity


data class MessageTransactionCategoryPOJO(
    @Embedded
    val messageEntity: MessageEntity?,
    @Embedded("transaction_")
    val transactionEntity: TransactionEntity?,
    @Embedded("category_")
    val categoryEntity: CategoryEntity?
)