package com.hegunhee.todolistwithflow.domain

import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.model.DefaultRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class GetAllMemoFlowUseCase @Inject constructor(private val repository: DefaultRepository): UseCase {

    operator fun invoke() : Flow<List<MemoEntity>>{
        return repository.getAllMemo()
    }
}