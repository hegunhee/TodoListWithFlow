package com.hegunhee.todolistwithflow.model

import com.hegunhee.todolistwithflow.data.MemoEntity

interface MemoRepository {

    suspend fun getAllMemo() : Result<List<MemoEntity>>

    suspend fun save(memoId : String) : Result<String>

    suspend fun deleteAll() : Result<Unit>

    suspend fun delete(memoId : String) : Result<String>

    suspend fun toggleMemo(memoId : String) : Result<String>
}