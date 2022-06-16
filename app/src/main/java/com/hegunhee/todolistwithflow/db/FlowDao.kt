package com.hegunhee.todolistwithflow.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hegunhee.todolistwithflow.data.MemoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FlowDao{

    @Query("SELECT * FROM MemoEntity")
    fun getAllMemo() : Flow<List<MemoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMemo(memo : MemoEntity)
}