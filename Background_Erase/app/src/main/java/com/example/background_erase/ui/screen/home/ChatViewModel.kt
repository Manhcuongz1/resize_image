package com.example.background_erase.ui.screen.home

import com.example.background_erase.base.BaseViewModel
import com.example.background_erase.base.Utils
import com.example.background_erase.model.ChatItem
import com.example.background_erase.model.mapper.toMessageUI
import dagger.hilt.android.lifecycle.HiltViewModel
import freelance.demoapp.domain.model.DataPrompt
import freelance.demoapp.domain.model.Message
import freelance.demoapp.domain.model.TransactionResponseFromLLM
import freelance.demoapp.domain.usecase.CreateMessageLLM
import freelance.demoapp.domain.usecase.ExtractTransactionsFromChatUseCase
import freelance.demoapp.domain.usecase.GetCategories
import freelance.demoapp.domain.usecase.InsertMessageUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val extractTransactionsFromChatUseCase: ExtractTransactionsFromChatUseCase,
    private val insertMessageUseCase: InsertMessageUseCase,
    private val createMessageLLM: CreateMessageLLM,
    private val getCategories: GetCategories
) : BaseViewModel() {
    private val _messages = MutableStateFlow<List<ChatItem>>(emptyList())
    val messages: StateFlow<List<ChatItem>> = _messages.asStateFlow()

    private val _typeCurrency = MutableStateFlow("đ")
    val typeCurrency: StateFlow<String> = _typeCurrency.asStateFlow()

    fun extractTransactions(textInput: String) {
        val message = createMessageUser(textInput)
        executeTask(
            action = {
                showMessageUserAndTyping(message)
                val dataPrompt = createDataPrompt(textInput, getCategories.invoke().map { it.name })
                val transactions = extractTransactionsFromChatUseCase.invoke(dataPrompt)
                if (transactions.isNotEmpty())
                    extractTransactionsSuccess(message, transactions)
                else
                    extractTransactionsError()
            },
            onSuccess = {

            }

        )
    }

    private fun extractTransactionsError() {
        _messages.update { old ->
            old.toMutableList().apply {
                removeFirstOrNull()
                removeFirstOrNull()
                add(0,ChatItem.Error("error"))
            }
        }
    }

    private suspend fun extractTransactionsSuccess(
        message: Message,
        transactions: List<TransactionResponseFromLLM>
    ) {
        val idMessageUser = insertMessageUseCase.invoke(message)

        val messageLLM = createMessageLLM.invoke(transactions)
        val idMessageLLM = insertMessageUseCase.invoke(messageLLM)

        val messageUserSaved = message.copy(id = idMessageUser)
        val messageLLMSaved = messageLLM.copy(id = idMessageLLM)

        _messages.update { old ->
            old.toMutableList().apply {
                removeFirstOrNull()
                removeFirstOrNull()
                add(0,ChatItem.Success(messageUserSaved.toMessageUI()))
                add(0,ChatItem.Success(messageLLMSaved.toMessageUI()))
            }
        }
    }

    private suspend fun showMessageUserAndTyping(message: Message) {
        _messages.update { old ->
            old.toMutableList().apply {
                add(0,ChatItem.Success(message.toMessageUI()))
            }
        }
        delay(100L)
        _messages.update { old ->
            old.toMutableList().apply {
                add(0,ChatItem.Typing)
            }
        }
    }

    private fun createDataPrompt(textInput: String, category: List<String> ): DataPrompt = DataPrompt(
        text = textInput,
        countryName = "Việt Nam",
        currencyUnit = "VND",
        language = "VN",
        date = Utils.getCurrentDate(),
        patternDate = Utils.PATTERN_DATE_DEFAULT,
        categories = category
    )

    private fun createMessageUser(textInput: String): Message = Message(
        id = -1,
        conversationId = 1,
        sender = Message.Sender.User,
        content = textInput,
        transaction = listOf()
    )

    private fun getCategory() {

    }

}