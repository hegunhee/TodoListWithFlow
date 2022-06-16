package com.hegunhee.todolistwithflow.model

import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.db.FlowDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate

class DefaultRepository(private val dao : FlowDao) : Repository{

    override fun getAllMemo(): Flow<List<MemoEntity>> {
        return dao.getAllMemo()
    }

    override fun insertMemo(memo: MemoEntity) {
        dao.insertMemo(memo)
    }

}