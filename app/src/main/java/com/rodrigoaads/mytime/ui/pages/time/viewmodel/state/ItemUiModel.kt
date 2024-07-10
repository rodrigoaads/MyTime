package com.rodrigoaads.mytime.ui.pages.time.viewmodel.state

import com.rodrigoaads.mytime.constants.StringConstants

data class ItemUiModel(
    val id: String,
    val name: String,
    val calculatingTime: String = StringConstants.EMPTY_STRING,
    val timeIn: String,
    val timeUntil: String,
    val showError: Boolean = false,
    val actionUrl: String
)