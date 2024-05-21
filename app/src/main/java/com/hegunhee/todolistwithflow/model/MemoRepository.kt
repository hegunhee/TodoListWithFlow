package com.hegunhee.todolistwithflow.model

import com.hegunhee.todolistwithflow.data.MemoEntity
import kotlinx.coroutines.flow.Flow

interface MemoRepository {

    fun getAllMemo() : Flow<List<MemoEntity>>

    suspend fun save(memo : MemoEntity)

    suspend fun deleteAll()

    suspend fun delete(memo : MemoEntity)

}