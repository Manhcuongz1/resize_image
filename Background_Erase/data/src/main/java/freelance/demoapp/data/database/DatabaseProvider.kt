package freelance.demoapp.data.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import freelance.demoapp.data.database.database.ChatDatabase
import freelance.demoapp.data.database.dto.ConversationEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime

object DatabaseProvider {

    private var INSTANCE: ChatDatabase? = null

    fun getDatabase(context: Context): ChatDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                ChatDatabase::class.java,
                "chat_database"
            ).addCallback( object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    insertInitialData(context)
                }
            }
            ).build()

            INSTANCE = instance

            instance
        }
    }
    private fun insertInitialData(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val dao = getDatabase(context).chatDao()
            val conversation = ConversationEntity(
                title = "User_${LocalDateTime.now()}"
            )
            val idConversation = dao.insertConversation(conversation)
            Log.d("Conversation","idConversation: $idConversation")
        }
    }
}