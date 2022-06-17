package com.hegunhee.todolistwithflow.ui

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.*
import com.hegunhee.todolistwithflow.data.MemoEntity
import com.hegunhee.todolistwithflow.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllMemoFlowUseCase: GetAllMemoFlowUseCase,
    private val insertMemoUseCase: InsertMemoUseCase,
    private val deleteAllMemoUseCase: DeleteAllMemoUseCase,
    private val deleteMemoUseCase: DeleteMemoUseCase,
) : ViewModel() {



    private var _editTextLiveData: MutableLiveData<String> = MutableLiveData("")
    val editTextLiveData: LiveData<String>
        get() = _editTextLiveData

    val memoListLiveData: LiveData<List<MemoEntity>> =
        editTextLiveData.asFlow().combine(getAllMemoFlowUseCase()) { str, list ->
            list.filter { it.text.contains(str) }
        }.asLiveData()


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


    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        val text = s.toString()
        _editTextLiveData.postValue(text)
        Log.d("ViewModelTest", s.toString())
    }


}