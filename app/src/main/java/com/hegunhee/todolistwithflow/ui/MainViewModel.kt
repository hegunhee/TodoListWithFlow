package com.hegunhee.todolistwithflow.ui

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
    private val getAllMemoListFlowUseCase: GetAllMemoListFlowUseCase,
    private val insertMemoUseCase: InsertMemoUseCase,
    private val deleteAllMemoUseCase: DeleteAllMemoUseCase,
    private val deleteMemoUseCase: DeleteMemoUseCase,
) : ViewModel(), MainActivityActionHandler {



    val editTextLiveData: MutableStateFlow<String> = MutableStateFlow<String>("")

    val memoListLiveData: Flow<List<MemoEntity>> =
        editTextLiveData.combine(getAllMemoListFlowUseCase()) { str, list ->
            list.filter { it.text.contains(str) }
        }.distinctUntilChanged()


    private var _event: MutableSharedFlow<Unit> = MutableSharedFlow()
    val event: SharedFlow<Unit> = _event.asSharedFlow()


    fun clickFloatingButton() = viewModelScope.launch{
        _event.emit(Unit)
    }

    fun insertMemo(text: String,isClear : Boolean = false) = viewModelScope.launch(Dispatchers.IO) {
        insertMemoUseCase(MemoEntity(text, isClear))
    }

    override fun toggleMemo(memo: MemoEntity) {
        viewModelScope.launch(Dispatchers.IO){
            insertMemoUseCase(memo.copy(isCheck = !memo.isCheck))
        }
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        deleteAllMemoUseCase()
    }

    override fun deleteMemo(memo: MemoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteMemoUseCase(memo)
        }
    }



}