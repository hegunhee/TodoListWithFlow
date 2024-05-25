package com.hegunhee.todolistwithflow.domain

import com.hegunhee.todolistwithflow.model.MemoRepository
import javax.inject.Inject

class ToggleMemoUseCase @Inject constructor(
    private val repository: MemoRepository
){

    suspend operator fun invoke(memoId: String) : Result<Unit> {
        return repository.toggleMemo(memoId)
    }
}