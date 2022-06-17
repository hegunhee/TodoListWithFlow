package com.hegunhee.todolistwithflow.model

import com.hegunhee.todolistwithflow.data.MemoEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getAllMemo() : Flow<List<MemoEntity>>

    suspend fun insertMemo(memo : MemoEntity)

    suspend fun deleteAll()

    suspend fun deleteMamo(memo : MemoEntity)
}