package freelance.demoapp.data.database.dto

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "messages",
    indices = [Index("conversationId")]
)
data class MessageEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val conversationId: Long,

    val sender: Enums.MessageSender,

    val content: String,

    val createdAt: Long = System.currentTimeMillis()
)