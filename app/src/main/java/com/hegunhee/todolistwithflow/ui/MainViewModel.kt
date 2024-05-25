package com.hegunhee.todolistwithflow.ui

import androidx.lifecycle.*
import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.IllegalStateException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllMemoListUseCase: GetAllMemoListUseCase,
    private val insertMemoUseCase: InsertMemoUseCase,
    private val deleteAllMemoUseCase: DeleteAllMemoUseCase,
    private val deleteMemoUseCase: DeleteMemoUseCase,
    private val toggleMemoUseCase: ToggleMemoUseCase
) : ViewModel(), MainActivityActionHandler {

    val searchQuery: MutableStateFlow<String> = MutableStateFlow<String>("")

    private val _memoList : MutableStateFlow<List<MemoEntity>> = MutableStateFlow(emptyList())
    val memoList: StateFlow<List<MemoEntity>> = _memoList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            searchQuery.collect { query ->
                getAllMemoListUseCase().onSuccess { result ->
                    if(query != "") {
                        _memoList.value = result.filter {
                            it.text.contains(query)
                        }
                    }else {
                        _memoList.value = result
                    }
                }
            }
        }
    }


    private var _navigationActions: MutableSharedFlow<MainNavigationAction> = MutableSharedFlow()
    val navigationActions: SharedFlow<MainNavigationAction> = _navigationActions.asSharedFlow()


    fun onAddMemoButtonClick() = viewModelScope.launch{
        _navigationActions.emit(MainNavigationAction.AddMemo)
    }

    fun insertMemo(memoId: String) = viewModelScope.launch(Dispatchers.IO) {
        insertMemoUseCase(memoId)
            .onFailure {
                if(it is IllegalStateException) {

                }
            }
        fetchMemo()
    }

    override fun toggleMemo(memoId: String) {
        viewModelScope.launch(Dispatchers.IO){
            toggleMemoUseCase(memoId)
            fetchMemo()
        }
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        deleteAllMemoUseCase()
        fetchMemo()

    }

    override fun deleteMemo(memoId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteMemoUseCase(memoId)
            fetchMemo()
        }
    }

    private suspend fun fetchMemo() {
        getAllMemoListUseCase()
            .onSuccess { result ->
                _memoList.value = result
            }.onFailure {

            }
    }
}