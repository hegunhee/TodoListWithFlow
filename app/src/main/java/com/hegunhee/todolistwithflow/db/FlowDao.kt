package com.hegunhee.todolistwithflow.db

import androidx.room.*
import com.hegunhee.todolistwithflow.data.MemoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FlowDao{

    @Query("SELECT * FROM MemoEntity ORDER BY text")
    fun getAllMemo() : Flow<List<MemoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemo(memo : MemoEntity)

    @Query("DELETE FROM MemoEntity")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteMemo(memo : MemoEntity)

    @Query("SELECT * FROM MemoEntity WHERE text LIKE :text ")
    fun getAllMemoBySearch(text : String) : Flow<List<MemoEntity>>
}