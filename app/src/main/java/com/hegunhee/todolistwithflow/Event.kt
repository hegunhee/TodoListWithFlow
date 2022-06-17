package com.hegunhee.todolistwithflow

sealed class Event(){
    object Uninitalized : Event()

    object Clicked : Event()
}