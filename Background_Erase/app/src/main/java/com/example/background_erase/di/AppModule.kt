package com.example.background_erase.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import freelance.demoapp.data.database.DatabaseProvider
import freelance.demoapp.data.database.database.ChatDatabase
import freelance.demoapp.data.database.shareprf.AppPreferences
import freelance.demoapp.domain.repository.CategoryRepository
import freelance.demoapp.domain.repository.ChatRepository
import freelance.demoapp.domain.repository.LLMRepository
import freelance.demoapp.domain.repository.TransactionRepository
import freelance.demoapp.domain.usecase.CreateMessageLLM
import freelance.demoapp.domain.usecase.CreateNewCategory
import freelance.demoapp.domain.usecase.ExtractTransactionsFromChatUseCase
import freelance.demoapp.domain.usecase.GetAnalyticUseCase
import freelance.demoapp.domain.usecase.GetCategories
import freelance.demoapp.domain.usecase.InsertMessageUseCase

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideExtractTransactionsFromChatUseCase(r: LLMRepository): ExtractTransactionsFromChatUseCase {
        return ExtractTransactionsFromChatUseCase(r)
    }

    @Provides
    fun provideInsertMessageUseCase(c: ChatRepository): InsertMessageUseCase {
        return InsertMessageUseCase(c)
    }

    @Provides
    fun provideGetAnalyticUseCase(t: TransactionRepository): GetAnalyticUseCase {
        return GetAnalyticUseCase(t)
    }

    @Provides
    fun provideDataBaseProvider(@ApplicationContext context: Context) : ChatDatabase {
        return DatabaseProvider.getDatabase(context)
    }

    @Provides
    fun provideAppPreferences(@ApplicationContext context: Context): AppPreferences {
        return AppPreferences(context)
    }

    @Provides
    fun provideCreateNewCategoryUseCase(c: CategoryRepository): CreateNewCategory {
        return CreateNewCategory(c)
    }

    @Provides
    fun provideGetCategoriesUseCase(c: CategoryRepository): GetCategories {
        return GetCategories(c)
    }

    @Provides
    fun provideCreateMessageLLMUseCase(c : CategoryRepository): CreateMessageLLM {
        return CreateMessageLLM(c)
    }

}