package com.rodrigoaads.mytime.ui.pages.time.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoaads.mytime.domain.entity.ActionState
import com.rodrigoaads.mytime.domain.usecase.ChangeTimeInUseCase
import com.rodrigoaads.mytime.domain.usecase.ChangeTimeUntilUseCase
import com.rodrigoaads.mytime.domain.usecase.GetListUseCase
import com.rodrigoaads.mytime.ui.pages.time.viewmodel.state.TimeState
import com.rodrigoaads.mytime.ui.pages.time.viewmodel.state.TimeUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimeViewModel(
    private val getListUseCase: GetListUseCase,
    private val changeTimeInUseCase: ChangeTimeInUseCase,
    private val changeTimeUntilUseCase: ChangeTimeUntilUseCase
): ViewModel() {

    private val _state = MutableSharedFlow<TimeState>()
    val state: SharedFlow<TimeState> = _state.asSharedFlow()

    private val _uiState = MutableStateFlow(TimeUiState())
    val uiState: StateFlow<TimeUiState> = _uiState.asStateFlow()

    init {
        getList()
    }

    private fun getList() {
        viewModelScope.launch {
            getListUseCase.invoke().collect { list ->
                _uiState.update { state ->
                    state.copy(
                        list = list
                    )
                }
            }
        }
    }

    fun changeTimeIn(
        id: String,
        timeIn: String
    ) {
        setInTimeLoading(true)
        viewModelScope.launch {
            changeTimeInUseCase.invoke(
                id = id,
                timeIn = timeIn
            ).let { actionState ->
                setInTimeLoading(false)
                when(actionState) {
                    is ActionState.Error -> _state.emit(TimeState.Error(actionState.message ?: ""))
                    else -> manageInTimePicker(show = false)
                }
            }
        }
    }

    fun changeTimeUntil(
        id: String,
        timeUntil: String
    ) {
        setUntilTimeLoading(true)
        viewModelScope.launch {
            changeTimeUntilUseCase.invoke(
                id = id,
                timeUntil = timeUntil
            ).let { actionState ->
                setUntilTimeLoading(false)
                when(actionState) {
                    is ActionState.Error -> _state.emit(TimeState.Error(actionState.message ?: ""))
                    else -> manageUntilTimePicker(show = false)
                }
            }
        }
    }

    private fun setInTimeLoading(show: Boolean) {
        _uiState.update { state ->
            state.copy(
                isChangeInTimeLoading = show
            )
        }
    }

    private fun setUntilTimeLoading(show: Boolean) {
        _uiState.update { state ->
            state.copy(
                isChangeUntilTimeLoading = show
            )
        }
    }

    fun manageInTimePicker(
        id: String = "",
        show: Boolean
    ) {
        _uiState.update { state ->
            state.copy(
                showInTimePicker = Pair(
                    first = id,
                    second = show
                )
            )
        }
    }

    fun manageUntilTimePicker(
        id: String = "",
        show: Boolean
    ) {
        _uiState.update { state ->
            state.copy(
                showUntilTimePicker = Pair(
                    first = id,
                    second = show
                )
            )
        }
    }
}