package com.hegunhee.todolistwithflow.ui

sealed class Event(){
    object Uninitalized : Event()

    object Clicked : Event()
}