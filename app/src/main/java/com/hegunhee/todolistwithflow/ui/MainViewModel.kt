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

    val searchQuery: MutableStateFlow<String> = MutableStateFlow<String>("")

    val memoList: StateFlow<List<MemoEntity>> =
        searchQuery.combine(getAllMemoListFlowUseCase()) { str, list ->
            list.filter { it.text.contains(str) }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )


    private var _navigationActions: MutableSharedFlow<Unit> = MutableSharedFlow()
    val navigationActions: SharedFlow<Unit> = _navigationActions.asSharedFlow()


    fun onAddMemoButtonClick() = viewModelScope.launch{
        _navigationActions.emit(Unit)
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