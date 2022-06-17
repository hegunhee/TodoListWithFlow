package com.hegunhee.todolistwithflow.domain

import android.util.Log
import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.model.DefaultRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@InstallIn(SingletonComponent::class)
@Module
class GetAllMemoBySearchUseCase @Inject constructor(private val repository: DefaultRepository)  : UseCase {

    operator fun invoke(searchText : String) : Flow<List<MemoEntity>>{
        return if(searchText == ""){
            Log.d("invokeTest","isEmpty")
            repository.getAllMemo()
        }else{
            Log.d("invokeTest","isNotEmpty $searchText")
            repository.getAllMemoBySearch("%$searchText%")
        }
    }
}