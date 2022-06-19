package com.hegunhee.todolistwithflow.model

import android.util.Log
import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.db.FlowDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate

class DefaultRepository(private val dao : FlowDao) : Repository{

    override fun getAllMemo(): Flow<List<MemoEntity>> {
        return dao.getAllMemo()
    }

    override suspend fun insertMemo(memo: MemoEntity) {
        dao.insertMemo(memo)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

    override suspend fun deleteMamo(memo: MemoEntity) {
        dao.deleteMemo(memo)
    }

}