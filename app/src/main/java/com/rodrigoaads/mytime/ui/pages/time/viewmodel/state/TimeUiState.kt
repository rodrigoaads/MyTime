package com.rodrigoaads.mytime.ui.pages.time.viewmodel.state

import com.rodrigoaads.mytime.constants.StringConstants

data class TimeUiState(
    val list: List<ItemUiModel>? = null,
    val isChangeInTimeLoading: Boolean = false,
    val isChangeUntilTimeLoading: Boolean = false,
    val showInTimePicker: Pair<String, Boolean> = Pair(
        first = StringConstants.EMPTY_STRING,
        second = false
    ),
    val showUntilTimePicker: Pair<String, Boolean> = Pair(
        first = StringConstants.EMPTY_STRING,
        second = false
    ),
    val totalTime: String = StringConstants.EMPTY_STRING,
    val date: String = StringConstants.EMPTY_STRING,
)