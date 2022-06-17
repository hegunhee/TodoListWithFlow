package com.hegunhee.todolistwithflow

import androidx.lifecycle.*
import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.domain.DeleteAllMemoUseCase
import com.hegunhee.todolistwithflow.domain.DeleteMemoUseCase
import com.hegunhee.todolistwithflow.domain.GetAllMemoFlowUseCase
import com.hegunhee.todolistwithflow.domain.InsertMemoUseCase
import com.hegunhee.todolistwithflow.model.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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


}