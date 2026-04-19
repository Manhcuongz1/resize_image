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
    Transaction.Type.Expense -> TransactionUI.Type.Expense
    Transaction.Type.Income -> TransactionUI.Type.Income
}

fun TransactionUI.Type.toTransactionType() = when (this) {
    TransactionUI.Type.Expense -> Transaction.Type.Expense
    TransactionUI.Type.Income -> Transaction.Type.Income
}

fun Message.Sender.toMessageUISender() = when (this) {
    Message.Sender.User -> MessageUI.Sender.User
    Message.Sender.LLM -> MessageUI.Sender.LLM
}
fun Category.toCategoryUI() = CategoryUI(
    id = id,
    name = name,
    type = type.toTransactionUIType(),
    colorBackground = colorBackground
)
fun Transaction.toTransactionUI() = TransactionUI(
    type = type.toTransactionUIType(),
    dateLabel = dateLabel,
    amount = amount,
    note = note,
    category = category.toCategoryUI()
)

fun CategoryUI.toCategory() = Category(
    id = id,
    name = name,
    type = type.toTransactionType(),
    colorBackground = colorBackground
)