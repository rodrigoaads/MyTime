package com.rodrigoaads.mytime.ui.pages.time.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoaads.mytime.domain.usecase.GetListUseCase
import com.rodrigoaads.mytime.ui.pages.time.viewmodel.state.TimeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimeViewModel(
    private val getListUseCase: GetListUseCase
): ViewModel() {

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
}