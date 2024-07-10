package com.rodrigoaads.mytime.ui.pages.register.viewmodel.state

import com.rodrigoaads.mytime.constants.StringConstants

data class RegisterUiState(
    val id: String = StringConstants.EMPTY_STRING,
    val name: String = StringConstants.EMPTY_STRING,
    val actionUrl: String = StringConstants.EMPTY_STRING,
    val showDeleteDialog: Boolean = false,
    val isLoading: Boolean = false
)