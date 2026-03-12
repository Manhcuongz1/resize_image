package com.example.background_erase.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import freelance.demoapp.domain.repository.TransactionsRepository
import freelance.demoapp.domain.usecase.ExtractTransactionsFromChatUseCase

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideExtractTransactionsFromChatUseCase(r: TransactionsRepository): ExtractTransactionsFromChatUseCase {
        return ExtractTransactionsFromChatUseCase(r)
    }
}