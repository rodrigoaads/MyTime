package com.rodrigoaads.mytime.ui.pages.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoaads.mytime.constants.StringConstants
import com.rodrigoaads.mytime.domain.entity.ActionState
import com.rodrigoaads.mytime.domain.usecase.CreateItemUseCase
import com.rodrigoaads.mytime.domain.usecase.DeleteItemUseCase
import com.rodrigoaads.mytime.domain.usecase.GetItemByIdUseCase
import com.rodrigoaads.mytime.domain.usecase.UpdateNameAndActionUseCase
import com.rodrigoaads.mytime.ui.pages.register.viewmodel.state.RegisterState
import com.rodrigoaads.mytime.ui.pages.register.viewmodel.state.RegisterUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val createItemUseCase: CreateItemUseCase,
    private val updateNameAndActionUseCase: UpdateNameAndActionUseCase,
    private val getItemByIdUseCase: GetItemByIdUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
): ViewModel() {
    
    private val _state = MutableSharedFlow<RegisterState>()
    val state: SharedFlow<RegisterState> = _state.asSharedFlow()
    
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun changeNameText(text: String) {
        _uiState.update { state ->
            state.copy(
                name = text
            )
        }
    }

    fun changeActionUrlText(text: String) {
        _uiState.update { state ->
            state.copy(
                actionUrl = text
            )
        }
    }

    fun manageDeleteDialog(show: Boolean) {
        _uiState.update { state ->
            state.copy(
                showDeleteDialog = show
            )
        }
    }
    
    fun createItem() {
        setLoading(true)
        viewModelScope.launch {
            createItemUseCase.invoke(
                name = uiState.value.name,
                actionUrl = uiState.value.actionUrl
            ).run {
                setLoading(false)
                when(this) {
                    is ActionState.Error -> _state.emit(RegisterState.Error)
                    is ActionState.Success -> _state.emit(RegisterState.SuccessAction)
                }
            }
        }
    }

    fun updateNameAndActionUseCase() {
        setLoading(true)
        viewModelScope.launch {
            updateNameAndActionUseCase.invoke(
                id = uiState.value.id,
                name = uiState.value.name,
                actionUrl = uiState.value.actionUrl
            ).run {
                setLoading(false)
                when(this) {
                    is ActionState.Error -> _state.emit(RegisterState.Error)
                    is ActionState.Success -> _state.emit(RegisterState.SuccessAction)
                }
            }
        }
    }

    fun getItemById(id: String) {
        setLoading(true)
        viewModelScope.launch {
            getItemByIdUseCase.invoke(id).run {
                setLoading(false)
                when(this) {
                    is ActionState.Error -> _state.emit(RegisterState.Error)
                    is ActionState.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                id = this.item?.id ?: StringConstants.EMPTY_STRING,
                                name = this.item?.name ?: StringConstants.EMPTY_STRING,
                                actionUrl = this.item?.actionUrl ?: StringConstants.EMPTY_STRING
                            )
                        }
                    }
                }
            }
        }
    }

    fun deleteItem() {
        setLoading(true)
        viewModelScope.launch {
            deleteItemUseCase.invoke(uiState.value.id).run {
                setLoading(false)
                when(this) {
                    is ActionState.Error -> _state.emit(RegisterState.Error)
                    is ActionState.Success -> _state.emit(RegisterState.SuccessAction)
                }
            }
        }
    }

    private fun setLoading(show: Boolean) {
        _uiState.update { state ->
            state.copy(
                isLoading = show
            )
        }
    }
}