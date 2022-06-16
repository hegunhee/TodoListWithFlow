package com.hegunhee.todolistwithflow.di

import android.content.Context
import androidx.room.Room
import com.hegunhee.todolistwithflow.db.FlowDao
import com.hegunhee.todolistwithflow.db.FlowDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideFlowDatabase(@ApplicationContext context : Context) : FlowDataBase {
        return Room.databaseBuilder(context,FlowDataBase::class.java,FlowDataBase.appName).build()
    }

    @Singleton
    @Provides
    fun provideFlowDao(dataBase: FlowDataBase) : FlowDao{
        return dataBase.getFlowDao()
    }
}