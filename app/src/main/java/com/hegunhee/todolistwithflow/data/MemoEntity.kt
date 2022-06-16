package com.hegunhee.todolistwithflow.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MemoEntity(@PrimaryKey val text : String) {

}