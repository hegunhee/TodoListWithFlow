package com.hegunhee.todolistwithflow.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hegunhee.todolistwithflow.data.MemoEntity

@Database(entities = [MemoEntity::class], version = 1)
abstract class FlowDataBase : RoomDatabase(){

    abstract fun getFlowDao() : FlowDao
    companion object{
        const val appName = "flowDatabase.db"
    }

}