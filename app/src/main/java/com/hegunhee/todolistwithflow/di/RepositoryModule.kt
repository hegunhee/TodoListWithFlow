package com.hegunhee.todolistwithflow.di

import com.hegunhee.todolistwithflow.model.DefaultMemoRepository
import com.hegunhee.todolistwithflow.model.MemoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideDefaultRepository(
        defaultRepository: DefaultMemoRepository
    ) : MemoRepository
}