package com.hegunhee.todolistwithflow.domain

import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.model.DefaultRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class DeleteMemoUseCase @Inject constructor(private val repository: DefaultRepository): UseCase {

    suspend operator fun invoke(memo : MemoEntity){
        repository.deleteMamo(memo)
    }
}