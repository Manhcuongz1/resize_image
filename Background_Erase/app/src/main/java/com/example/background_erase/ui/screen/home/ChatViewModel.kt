package com.example.background_erase.ui.screen.home

import com.example.background_erase.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import freelance.demoapp.domain.usecase.ExtractTransactionsFromChatUseCase

@HiltViewModel
class ChatViewModel(
    private val extractTransactionsFromChatUseCase: ExtractTransactionsFromChatUseCase
) : BaseViewModel() {

}