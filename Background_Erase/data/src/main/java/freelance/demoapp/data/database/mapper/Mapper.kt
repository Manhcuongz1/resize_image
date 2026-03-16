package freelance.demoapp.data.database.mapper


import freelance.demoapp.data.database.dto.Enums
import freelance.demoapp.data.database.dto.MessageEntity
import freelance.demoapp.domain.model.Message

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
