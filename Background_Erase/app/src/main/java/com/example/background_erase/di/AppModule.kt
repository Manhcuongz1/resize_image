package com.example.background_erase.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import freelance.demoapp.data.database.DatabaseProvider
import freelance.demoapp.data.database.database.ChatDatabase
import freelance.demoapp.domain.repository.ChatRepository
import freelance.demoapp.domain.repository.TransactionsRepository
import freelance.demoapp.domain.usecase.ExtractTransactionsFromChatUseCase
import freelance.demoapp.domain.usecase.InsertMessageUseCase

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideExtractTransactionsFromChatUseCase(r: TransactionsRepository): ExtractTransactionsFromChatUseCase {
        return ExtractTransactionsFromChatUseCase(r)
    }

    @Provides
    fun provideInsertMessageUseCase(c: ChatRepository): InsertMessageUseCase {
        return InsertMessageUseCase(c)
    }


    @Provides
    fun provideDataBaseProvider(@ApplicationContext context: Context) : ChatDatabase {
        return DatabaseProvider.getDatabase(context)
    }
}