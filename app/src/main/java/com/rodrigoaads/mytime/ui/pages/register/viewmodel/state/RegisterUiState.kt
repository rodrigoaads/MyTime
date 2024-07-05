package com.rodrigoaads.mytime.ui.pages.register.viewmodel.state

data class RegisterUiState(
    val id: String = "",
    val name: String = "",
    val actionUrl: String = "",
    val showDeleteDialog: Boolean = false,
    val isLoading: Boolean = false
)