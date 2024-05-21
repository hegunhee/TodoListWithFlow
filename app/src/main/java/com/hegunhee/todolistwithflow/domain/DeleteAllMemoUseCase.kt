package com.hegunhee.todolistwithflow.domain

import com.hegunhee.todolistwithflow.model.MemoRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class DeleteAllMemoUseCase @Inject constructor(private val memoRepository: MemoRepository) {

    suspend operator fun invoke() : Result<Unit> {
        return memoRepository.deleteAll()
    }
}