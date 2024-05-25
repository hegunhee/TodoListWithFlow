package com.hegunhee.todolistwithflow.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemoEntity(
    @SerialName("text")val text : String,
    @SerialName("isCheck") val isCheck : Boolean
)