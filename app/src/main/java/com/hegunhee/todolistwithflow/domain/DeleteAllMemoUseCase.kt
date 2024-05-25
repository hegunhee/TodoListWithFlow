package com.hegunhee.todolistwithflow.domain

import com.hegunhee.todolistwithflow.model.MemoRepository
import javax.inject.Inject

class DeleteAllMemoUseCase @Inject constructor(private val memoRepository: MemoRepository) {

    suspend operator fun invoke() : Result<Unit> {
        return memoRepository.deleteAll()
    }
}