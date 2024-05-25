package com.hegunhee.todolistwithflow.api

import com.hegunhee.todolistwithflow.data.MemoEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TodoApi {

    @GET("todo")
    suspend fun getAllMemo() : List<MemoEntity>

    @GET("todo/{id}")
    suspend fun getMemo(@Path("id") id : String) : MemoEntity

    @POST("todo")
    suspend fun save(@Body todoForm : TodoForm) : TodoForm

    @PATCH("todo/{id}")
    suspend fun toggleMemo(@Path("id") id : String) : Unit

    @DELETE("todo/{id}")
    suspend fun deleteTodo(@Path("id") id : String) : Unit

    @DELETE("todo")
    suspend fun deleteAllTodo() : Unit
}