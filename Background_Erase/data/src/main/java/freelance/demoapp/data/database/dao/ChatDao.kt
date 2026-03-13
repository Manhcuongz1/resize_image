package freelance.demoapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import freelance.demoapp.data.database.dto.ConversationEntity
import freelance.demoapp.data.database.dto.MessageEntity
import freelance.demoapp.data.database.dto.TransactionEntity
import freelance.demoapp.data.database.dto.pojo.MessageTransactionCategoryPOJO

@Dao
interface ChatDao {

    @Insert
    suspend fun insertConversation(conversation: ConversationEntity): Long

    @Query("SELECT * FROM conversations")
    suspend fun getConversations(): List<ConversationEntity>


    @Insert
    suspend fun insertMessage(message: MessageEntity): Long

    @Query(
    """
        SELECT * FROM messages
        WHERE conversationId = :conversationId
        ORDER BY createdAt
    """
    )
    suspend fun getMessages(conversationId: Long): List<MessageEntity>


    @Insert
    suspend fun insertTransactions(transactions: List<TransactionEntity>)

    @Query("""
        SELECT * FROM transactions
        WHERE messageId = :messageId
    """)
    suspend fun getTransactions(messageId: Long): List<TransactionEntity>

    @Query(value = """
        SELECT m.*,
        
        t.id AS transaction_id,
        t.messageId AS transaction_messageId,
        t.categoryId AS transaction_categoryId,
        t.amount AS transaction_amount,
        t.description AS transaction_description,
        t.transactionDate AS transaction_transactionDate,
        t.createdAt AS transaction_createdAt,
        
        c.id AS category_id,
        c.name AS category_name,
        c.type AS category_type,
        c.createdAt AS category_createdAt
        
        FROM (
            SELECT *
            FROM messages
            WHERE conversationId = :conversationId
            ORDER BY createdAt DESC
            LIMIT 30 OFFSET :offset
        ) m
        
        LEFT JOIN transactions t ON t.messageId = m.id
        LEFT JOIN categories c ON c.id = t.categoryId
        
        ORDER BY m.createdAt DESC
    """
    )
    suspend fun getMessagesWithTransactionCategory(
        conversationId: Long, offset: Long
    ): List<MessageTransactionCategoryPOJO>

}