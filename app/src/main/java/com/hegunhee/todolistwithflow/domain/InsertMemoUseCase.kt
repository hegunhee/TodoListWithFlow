package com.hegunhee.todolistwithflow.domain

import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.model.DefaultRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class InsertMemoUseCase @Inject constructor(private val repository: DefaultRepository) : UseCase {

    suspend operator fun invoke(memo : MemoEntity){
        repository.insertMemo(memo)
    }
}