package com.example.background_erase.model.mapper

import com.example.background_erase.model.CategoryUI
import com.example.background_erase.model.MessageUI
import com.example.background_erase.model.TransactionUI
import freelance.demoapp.domain.model.Category
import freelance.demoapp.domain.model.Message
import freelance.demoapp.domain.model.Transaction

fun Message.toMessageUI() = MessageUI(
    id = id,
    conversationId = conversationId,
    sender = sender.toMessageUISender(),
    content = content,
    createdAt = createdAt,
    transaction = transaction.map { it.toTransactionUI() }
)

fun Transaction.Type.toTransactionUIType() = when (this) {
    Transaction.Type.EXPENSE -> TransactionUI.Type.EXPENSE
    Transaction.Type.INCOME -> TransactionUI.Type.INCOME
}

fun Message.Sender.toMessageUISender() = when (this) {
    Message.Sender.User -> MessageUI.Sender.User
    Message.Sender.LLM -> MessageUI.Sender.LLM
}
fun Category.toCategoryUI() = CategoryUI(
    id = id,
    name = name,
    type = type.toTransactionUIType()
)
fun Transaction.toTransactionUI() = TransactionUI(
    type = type.toTransactionUIType(),
    dateLabel = dateLabel,
    amount = amount,
    note = note,
    category = category.toCategoryUI()
)