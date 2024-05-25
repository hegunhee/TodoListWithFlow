package com.hegunhee.todolistwithflow.model

import com.hegunhee.todolistwithflow.data.MemoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultMemoRepository @Inject constructor() : MemoRepository {

    override fun getAllMemo(): Flow<List<MemoEntity>> {
        return return flow {  }
    }

    override suspend fun save(memo: MemoEntity): Result<String> {
        return runCatching {
            ""
        }
    }

    override suspend fun deleteAll(): Result<Unit> {
        return runCatching {  }
    }

    override suspend fun delete(memo: MemoEntity): Result<String> {
        return runCatching {
            ""
        }
    }

    override suspend fun toggleMemo(memoId: String): Result<String> {
        return runCatching {
            ""
        }
    }
}