package freelance.demoapp.data.database.mapper


import freelance.demoapp.data.database.dto.CategoryEntity
import freelance.demoapp.data.database.dto.Enums
import freelance.demoapp.data.database.dto.MessageEntity
import freelance.demoapp.data.database.dto.TransactionEntity
import freelance.demoapp.domain.model.Category
import freelance.demoapp.domain.model.Message
import freelance.demoapp.domain.model.Transaction

fun Message.toMessageEntity() : MessageEntity {
    return MessageEntity(
        conversationId = conversationId,
        sender = sender.toEnumsMessageSender(),
        content = content,
        createdAt = createdAt,
    )
}

fun Enums.MessageSender.toMessageSender(): Message.Sender {
    return if (this == Enums.MessageSender.User) {
        Message.Sender.User
    } else {
        Message.Sender.LLM
    }
}

fun Message.Sender.toEnumsMessageSender(): Enums.MessageSender {
    return if (this == Message.Sender.User) {
        Enums.MessageSender.User
    } else {
        Enums.MessageSender.LLM
    }
}

fun CategoryEntity.toCategory(): Category {
    return Category(
        id = id,
        name = name,
        colorBackground = colorBackground,
        type = type.toTransactionType(),
        createdAt = createdAt
    )
}

fun Enums.TypeTransaction.toTransactionType(): Transaction.Type = when (this) {
    Enums.TypeTransaction.Income -> Transaction.Type.Income
    Enums.TypeTransaction.Expense -> Transaction.Type.Expense
}

fun Transaction.Type.toEnumsTypeTransaction(): Enums.TypeTransaction = when(this) {
    Transaction.Type.Income -> Enums.TypeTransaction.Income
    Transaction.Type.Expense -> Enums.TypeTransaction.Expense
}

fun TransactionEntity.toTransaction(category: Category): Transaction {
    return Transaction(
        type = type.toTransactionType(),
        dateLabel = transactionDate,
        amount = amount,
        note = description,
        category = category
    )

}

fun Transaction.toTransactionEntity(messageId: Long): TransactionEntity {
    return TransactionEntity(
        categoryId = category.id,
        messageId = messageId,
        amount = amount,
        description = note,
        type = type.toEnumsTypeTransaction(),
        transactionDate = dateLabel,
    )
}

fun Category.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        name = name,
        colorBackground = colorBackground,
        type = type.toEnumsTypeTransaction(),
        createdAt = createdAt
    )
}