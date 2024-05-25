package com.hegunhee.todolistwithflow.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoForm(
    @SerialName("todoId")val text : String,
)