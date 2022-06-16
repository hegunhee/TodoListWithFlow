package com.hegunhee.todolistwithflow.di

import com.hegunhee.todolistwithflow.db.FlowDao
import com.hegunhee.todolistwithflow.model.DefaultRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDefaultRepository(
        dao : FlowDao
    ) : DefaultRepository{
        return DefaultRepository(dao)
    }
}