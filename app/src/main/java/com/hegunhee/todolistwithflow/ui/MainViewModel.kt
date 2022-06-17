package com.hegunhee.todolistwithflow.ui

import androidx.lifecycle.*
import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.domain.DeleteAllMemoUseCase
import com.hegunhee.todolistwithflow.domain.DeleteMemoUseCase
import com.hegunhee.todolistwithflow.domain.GetAllMemoFlowUseCase
import com.hegunhee.todolistwithflow.domain.InsertMemoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllMemoFlowUseCase: GetAllMemoFlowUseCase,
    private val insertMemoUseCase: InsertMemoUseCase,
    private val deleteAllMemoUseCase: DeleteAllMemoUseCase,
    private val deleteMemoUseCase: DeleteMemoUseCase
) : ViewModel() {

    val memoListLiveData: LiveData<List<MemoEntity>> = getAllMemoFlowUseCase().asLiveData()

    private var _event: MutableLiveData<Event> = MutableLiveData(Event.Uninitalized)
    val event: LiveData<Event>
        get() = _event


    fun clickFloatingButton() {
        _event.postValue(Event.Clicked)
    }

    fun insertMemo(text: String) = viewModelScope.launch(Dispatchers.IO) {
        insertMemoUseCase(MemoEntity(text, false))

    }

    fun reverseCheckInsertMemo(memo: MemoEntity) = viewModelScope.launch(Dispatchers.IO) {
        insertMemoUseCase(memo)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        deleteAllMemoUseCase()
    }

    fun deleteMemo(memo: MemoEntity) = viewModelScope.launch(Dispatchers.IO) {
        deleteMemoUseCase(memo)
    }

    suspend fun test(str : String)  {
        val a = getAllMemoFlowUseCase().collect {
            it.filter { it.text.contains(str) }
        }

    }


}