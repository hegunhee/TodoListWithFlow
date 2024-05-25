package com.hegunhee.todolistwithflow.domain

import com.hegunhee.todolistwithflow.model.MemoRepository
import javax.inject.Inject

class InsertMemoUseCase @Inject constructor(private val memoRepository: MemoRepository) {

    suspend operator fun invoke(memoId : String) : Result<String> {
        return memoRepository.save(memoId)
    }
}