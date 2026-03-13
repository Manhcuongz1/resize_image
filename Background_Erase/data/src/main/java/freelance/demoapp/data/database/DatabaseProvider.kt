package freelance.demoapp.data.database

import android.content.Context
import androidx.room.Room
import freelance.demoapp.data.database.database.ChatDatabase

object DatabaseProvider {

    private var INSTANCE: ChatDatabase? = null

    fun getDatabase(context: Context): ChatDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                ChatDatabase::class.java,
                "chat_database"
            ).build()

            INSTANCE = instance

            instance
        }
    }
}