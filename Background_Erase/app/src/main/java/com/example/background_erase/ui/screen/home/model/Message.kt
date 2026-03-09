package com.example.background_erase.ui.screen.home.model

data class Message (
    val text: String = "",
    val sender: Sender = Sender.User,
    val typeMessage : TypeMessage = TypeMessage.Text,
    val time: String? = null
) {
    fun isUser() = sender == Sender.User
    sealed class Sender {
        object Gemini : Sender()
        object LLMLocal : Sender()
        object User : Sender()
    }
    sealed class TypeMessage {
        object Text : TypeMessage()
        object Image : TypeMessage()
    }
}