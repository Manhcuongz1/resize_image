package com.example.background_erase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import freelance.demoapp.data.repositoryImp.ChatRepositoryImp
import freelance.demoapp.data.repositoryImp.TransactionsRepositoryImp
import freelance.demoapp.domain.repository.ChatRepository
import freelance.demoapp.domain.repository.TransactionsRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindChatRepository(chatRepositoryImp: ChatRepositoryImp): ChatRepository

    @Binds
    abstract fun bindTransactionsRepository(transactionsRepositoryImp: TransactionsRepositoryImp): TransactionsRepository
}
