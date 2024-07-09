package com.rodrigoaads.mytime.ui.pages.time.viewmodel.state

import com.rodrigoaads.mytime.domain.entity.ItemModel

data class TimeUiState(
    val list: List<ItemModel>? = null,
    val isChangeInTimeLoading: Boolean = false,
    val isChangeUntilTimeLoading: Boolean = false,
    val showInTimePicker: Pair<String, Boolean> = Pair(
        first = "",
        second = false
    ),
    val showUntilTimePicker: Pair<String, Boolean> = Pair(
        first = "",
        second = false
    )
)