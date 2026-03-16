package com.example.background_erase.model

sealed interface ChatItem {
    object Typing : ChatItem
    class Success(val messageUI: MessageUI) : ChatItem

    class Error(val err : String) : ChatItem
}