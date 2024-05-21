package com.hegunhee.todolistwithflow.domain

import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.model.MemoRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class GetAllMemoListFlowUseCase @Inject constructor(private val memoRepository: MemoRepository) {

    operator fun invoke() : Flow<List<MemoEntity>>{
        return memoRepository.getAllMemo()
    }
}