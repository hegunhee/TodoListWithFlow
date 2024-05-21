package com.hegunhee.todolistwithflow.model

import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.db.FlowDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultMemoRepository @Inject constructor(private val dao: FlowDao) : MemoRepository {

    override fun getAllMemo(): Flow<List<MemoEntity>> {
        return dao.getAllMemoList()
    }

    override suspend fun save(memo: MemoEntity): Result<Unit> {
        return runCatching { dao.insertMemo(memo) }
    }

    override suspend fun deleteAll(): Result<Unit> {
        return runCatching { dao.deleteAll() }
    }

    override suspend fun delete(memo: MemoEntity): Result<Unit> {
        return runCatching { dao.deleteMemo(memo) }
    }

}