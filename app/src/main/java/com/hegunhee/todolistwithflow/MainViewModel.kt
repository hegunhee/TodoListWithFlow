package com.hegunhee.todolistwithflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.model.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository : DefaultRepository) : ViewModel(){

    val memoListLiveData : LiveData<List<MemoEntity>> = repository.getAllMemo().asLiveData()

    var testStr = "test"
    fun insertMemo(){
        //아직까지는 테스트코드
        // 원래는 event를 해서 옵저버패턴으로 view에 넘겨줘야함
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.insertMemo(MemoEntity(testStr))
                testStr += 1
            }
        }

    }

    fun deleteAll(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.deleteAll()
            }
        }
    }



}