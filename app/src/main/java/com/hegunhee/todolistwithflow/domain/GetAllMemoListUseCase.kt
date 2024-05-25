package com.hegunhee.todolistwithflow.domain

import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.model.MemoRepository
import javax.inject.Inject

class GetAllMemoListUseCase @Inject constructor(private val memoRepository: MemoRepository) {

    suspend operator fun invoke() : Result<List<MemoEntity>>{
        return memoRepository.getAllMemo()
    }
}