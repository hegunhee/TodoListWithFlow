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

    fun insertMemo(text: String) {
        //아직까지는 테스트코드
        // 원래는 event를 해서 옵저버패턴으로 view에 넘겨줘야함
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                insertMemoUseCase(MemoEntity(text))
            }
        }

    }

    fun deleteAll() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deleteAllMemoUseCase()
            }
        }
    }

    fun deleteMemo(memo: MemoEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deleteMemoUseCase(memo)
            }
        }
    }


}