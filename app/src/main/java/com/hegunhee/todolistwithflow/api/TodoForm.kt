package com.hegunhee.todolistwithflow.api

import kotlinx.serialization.Serializable

@Serializable
data class TodoForm(
    val todoId : String,
)