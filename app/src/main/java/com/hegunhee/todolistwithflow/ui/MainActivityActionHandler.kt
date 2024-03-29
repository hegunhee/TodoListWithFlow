package com.hegunhee.todolistwithflow.ui

import com.hegunhee.todolistwithflow.data.MemoEntity

interface MainActivityActionHandler {

    fun deleteMemo(memo : MemoEntity)

    fun toggleMemo(memo : MemoEntity)
}