package com.hegunhee.todolistwithflow.di

import com.hegunhee.todolistwithflow.model.DefaultRepository
import com.hegunhee.todolistwithflow.model.Repository
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
        defaultRepository: DefaultRepository
    ) : Repository
}