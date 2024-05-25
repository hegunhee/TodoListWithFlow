package com.hegunhee.todolistwithflow.model

import com.hegunhee.todolistwithflow.api.TodoApi
import com.hegunhee.todolistwithflow.api.TodoForm
import com.hegunhee.todolistwithflow.data.MemoEntity
import javax.inject.Inject

class DefaultMemoRepository @Inject constructor(
    private val todoApi: TodoApi
) : MemoRepository {

    override suspend fun getAllMemo(): Result<List<MemoEntity>> {
        return runCatching { todoApi.getAllMemo() }
    }

    override suspend fun save(memoId : String): Result<String> {
        return runCatching {
            todoApi.save(memoId.toForm())
        }
    }

    override suspend fun deleteAll(): Result<Unit> {
        return runCatching {
            todoApi.deleteAllTodo()
        }
    }

    override suspend fun delete(memoId : String): Result<String> {
        return runCatching {
            todoApi.deleteTodo(memoId)
        }
    }

    override suspend fun toggleMemo(memoId: String): Result<String> {
        return runCatching {
            todoApi.toggleMemo(memoId)
        }
    }
}

private fun String.toForm() : TodoForm {
    return TodoForm(this)
}