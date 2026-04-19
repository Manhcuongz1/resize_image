package com.example.background_erase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import freelance.demoapp.data.repositoryImp.CategoryRepositoryImp
import freelance.demoapp.data.repositoryImp.ChatRepositoryImp
import freelance.demoapp.data.repositoryImp.LLMRepositoryImp
import freelance.demoapp.data.repositoryImp.TransactionRepositoryImp
import freelance.demoapp.domain.repository.CategoryRepository
import freelance.demoapp.domain.repository.ChatRepository
import freelance.demoapp.domain.repository.LLMRepository
import freelance.demoapp.domain.repository.TransactionRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindChatRepository(chatRepositoryImp: ChatRepositoryImp): ChatRepository

    @Binds
    abstract fun bindLLMRepository(llmRepositoryImp: LLMRepositoryImp): LLMRepository

    @Binds
    abstract fun bindTransactionRepository(transactionRepositoryImp: TransactionRepositoryImp): TransactionRepository

    @Binds
    abstract fun bindCategoryRepository(
        c: CategoryRepositoryImp,
    ): CategoryRepository

}
