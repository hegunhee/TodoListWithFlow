package com.hegunhee.todolistwithflow.ui


interface MainActivityActionHandler {

    fun deleteMemo(memoId : String)

    fun toggleMemo(memoId : String)
}