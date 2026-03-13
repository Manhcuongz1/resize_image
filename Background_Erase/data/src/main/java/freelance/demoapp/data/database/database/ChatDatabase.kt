package freelance.demoapp.data.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import freelance.demoapp.data.database.dao.ChatDao
import freelance.demoapp.data.database.dto.CategoryEntity
import freelance.demoapp.data.database.dto.ConversationEntity
import freelance.demoapp.data.database.dto.MessageEntity
import freelance.demoapp.data.database.dto.TransactionEntity

@Database(
    entities = [
        ConversationEntity::class,
        MessageEntity::class,
        TransactionEntity::class,
        CategoryEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(RoomConverter::class)
abstract class ChatDatabase : RoomDatabase() {

    abstract fun chatDao(): ChatDao
}