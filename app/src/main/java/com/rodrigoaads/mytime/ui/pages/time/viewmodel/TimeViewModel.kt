package com.rodrigoaads.mytime.ui.pages.time.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoaads.mytime.domain.entity.ActionState
import com.rodrigoaads.mytime.domain.usecase.ChangeTimeInUseCase
import com.rodrigoaads.mytime.domain.usecase.ChangeTimeUntilUseCase
import com.rodrigoaads.mytime.domain.usecase.GetDateUseCase
import com.rodrigoaads.mytime.domain.usecase.GetListUseCase
import com.rodrigoaads.mytime.extension.getHourOrMinute
import com.rodrigoaads.mytime.extension.getInterval
import com.rodrigoaads.mytime.ui.pages.time.viewmodel.state.ItemUiModel
import com.rodrigoaads.mytime.ui.pages.time.viewmodel.state.TimeState
import com.rodrigoaads.mytime.ui.pages.time.viewmodel.state.TimeUiState
import com.rodrigoaads.mytime.constants.StringConstants
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
    private val changeTimeUntilUseCase: ChangeTimeUntilUseCase,
    private val getDateUseCase: GetDateUseCase
): ViewModel() {

    private val _state = MutableSharedFlow<TimeState>()
    val state: SharedFlow<TimeState> = _state.asSharedFlow()

    private val _uiState = MutableStateFlow(TimeUiState())
    val uiState: StateFlow<TimeUiState> = _uiState.asStateFlow()

    init {
        getList()
        _uiState.update { state ->
            state.copy(
                date = getDateUseCase.invoke()
            )
        }
    }

    private fun getList() {
        viewModelScope.launch {
            getListUseCase.invoke().collect { list ->
                _uiState.update { state ->
                    var totalTime = 0
                    state.copy(
                        list = list.map { item ->
                            val isValid = isValidInterval(
                                timeIn = item.timeIn,
                                timeUntil = item.timeUntil
                            )
                            val isTimeInEmpty = item.timeIn == StringConstants.INITIAL_TIME
                            val isTimeUntilEmpty = item.timeUntil == StringConstants.INITIAL_TIME
                            val timeCalc = if (isValid) timeCalc(
                                timeIn = item.timeIn,
                                timeUntil = item.timeUntil
                            ) else Pair(
                                first = 0,
                                second = StringConstants.EMPTY_STRING
                            )
                            totalTime += timeCalc.first
                            ItemUiModel(
                                id = item.id,
                                name = item.name,
                                calculatingTime = timeCalc.second,
                                timeIn = if (!isTimeInEmpty) item.timeIn
                                    else StringConstants.EMPTY_STRING,
                                timeUntil = if (!isTimeUntilEmpty) item.timeUntil
                                    else StringConstants.EMPTY_STRING,
                                showError = !isValid && !isTimeInEmpty && !isTimeUntilEmpty,
                                actionUrl = item.actionUrl
                            )
                        },
                        totalTime = "${totalTime / 60}h ${totalTime % 60}m"
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
                    is ActionState.Error -> _state.emit(TimeState.Error(actionState.message ?: StringConstants.EMPTY_STRING))
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
                    is ActionState.Error -> _state.emit(TimeState.Error(actionState.message ?: StringConstants.EMPTY_STRING))
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
        id: String = StringConstants.EMPTY_STRING,
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
        id: String = StringConstants.EMPTY_STRING,
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

    private fun isValidInterval(
        timeIn: String,
        timeUntil: String
    ): Boolean {
        val inWithInt = timeIn.getInterval()
        val untilWithInt = timeUntil.getInterval()
        return (inWithInt ?: 0) < (untilWithInt ?: 0)
    }

    private fun timeCalc(
        timeIn: String,
        timeUntil: String,
    ): Pair<Int, String> {
        return try {
            val timeInWithMinutes = ((timeIn.getHourOrMinute() ?: 0) * 60) + (timeIn.getHourOrMinute(false) ?: 0)
            val timeUntilWithMinutes = ((timeUntil.getHourOrMinute() ?: 0) * 60) + (timeUntil.getHourOrMinute(false) ?: 0)
            Pair(
                first = timeUntilWithMinutes - timeInWithMinutes,
                second = "${(timeUntilWithMinutes - timeInWithMinutes) / 60}h ${(timeUntilWithMinutes - timeInWithMinutes) % 60}m"
            )
        } catch (e: Exception) {
            Pair(
                first = 0,
                second = StringConstants.EMPTY_STRING
            )
        }
    }
}