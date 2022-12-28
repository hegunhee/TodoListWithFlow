package com.hegunhee.todolistwithflow.ui

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.*
import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllMemoFlowUseCase: GetAllMemoFlowUseCase,
    private val insertMemoUseCase: InsertMemoUseCase,
    private val deleteAllMemoUseCase: DeleteAllMemoUseCase,
    private val deleteMemoUseCase: DeleteMemoUseCase,
) : ViewModel() {



    val editTextLiveData: MutableStateFlow<String> = MutableStateFlow<String>("")

    val memoListLiveData: LiveData<List<MemoEntity>> =
        editTextLiveData.combine(getAllMemoFlowUseCase()) { str, list ->
            list.filter { it.text.contains(str) }
        }.asLiveData()


    private var _event: MutableSharedFlow<Unit> = MutableSharedFlow()
    val event: SharedFlow<Unit> = _event.asSharedFlow()


    fun clickFloatingButton() = viewModelScope.launch{
        _event.emit(Unit)
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