package freelance.demoapp.data.database.dto.pojo

import androidx.room.Embedded
import androidx.room.Relation
import freelance.demoapp.data.database.dto.ConversationEntity
import freelance.demoapp.data.database.dto.MessageEntity

data class ConversationWithMessagesPOJO(

    @Embedded
    val conversationEntity: ConversationEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "conversationId"
    )
    val messageEntities: List<MessageEntity>
)