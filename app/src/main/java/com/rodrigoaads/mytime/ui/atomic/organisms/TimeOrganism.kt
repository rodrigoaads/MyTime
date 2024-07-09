package com.rodrigoaads.mytime.ui.atomic.organisms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.domain.entity.ItemModel
import com.rodrigoaads.mytime.ui.atomic.molecules.TimeItemMolecule
import com.rodrigoaads.mytime.ui.theme.Dimen
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun TimeOrganism(
    timeList: List<ItemModel>,
    isChangeInTimeLoading: Boolean,
    isChangeUntilTimeLoading: Boolean,
    showInTimerPicker: Pair<String, Boolean>,
    showUntilTimerPicker: Pair<String, Boolean>,
    onDismissInTimePicker: () -> Unit,
    onDismissUntilTimePicker: () -> Unit,
    onShowInTimePicker: (String) -> Unit,
    onShowUntilTimePicker: (String) -> Unit,
    onTimeInChange: (String, String) -> Unit,
    onTimeUntilChange: (String, String) -> Unit,
    onClickAction: ((String) -> Unit)?,
    onClickCard: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimen.smallPadding),
    ) {
        items(timeList) { model ->
            TimeItemMolecule(
                text = model.name,
                calculatingTime = if (model.showError) ""
                    else model.calculatingTime,
                timeIn = model.timeIn,
                timeUntil = model.timeUntil,
                onTimeInChange = { time ->
                    onTimeInChange.invoke(model.id, time)
                },
                showError = model.showError,
                onTimeUntilChange = { time ->
                    onTimeUntilChange.invoke(model.id, time)
                },
                onClickAction = Pair(
                    first = model.actionUrl.isNotEmpty(),
                    second = {
                        onClickAction?.invoke(model.actionUrl)
                    }
                ),
                onClickCard = {
                    onClickCard.invoke(model.id)
                },
                isChangeInTimeLoading = isChangeInTimeLoading,
                isChangeUntilTimeLoading = isChangeUntilTimeLoading,
                showInTimerPicker = (showInTimerPicker.first == model.id) && showInTimerPicker.second,
                showUntilTimerPicker = (showUntilTimerPicker.first == model.id) && showUntilTimerPicker.second,
                onDismissInTimePicker = onDismissInTimePicker,
                onDismissUntilTimePicker = onDismissUntilTimePicker,
                onShowInTimePicker = {
                    onShowInTimePicker.invoke(model.id)
                },
                onShowUntilTimePicker = {
                    onShowUntilTimePicker.invoke(model.id)
                }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MyTimeTheme {
        TimeOrganism(
            timeList = listOf(
                ItemModel(
                    id = "0",
                    name = "Teste 1",
                    calculatingTime = "1h",
                    timeIn = "14:00",
                    timeUntil = "15:00",
                    showError = false,
                    actionUrl = ""
                ),
                ItemModel(
                    id = "1",
                    name = "Teste 2",
                    calculatingTime = "2h",
                    timeIn = "17:00",
                    timeUntil = "15:30",
                    showError = true,
                    actionUrl = "abc"
                )
            ),
            onTimeInChange = { _, _ -> },
            onTimeUntilChange = { _, _ -> },
            onClickAction = {},
            onClickCard = {},
            isChangeInTimeLoading = false,
            isChangeUntilTimeLoading = false,
            showInTimerPicker = Pair(
                first = "",
                second = false
            ),
            showUntilTimerPicker = Pair(
                first = "",
                second = false
            ),
            onDismissInTimePicker = {},
            onDismissUntilTimePicker = {},
            onShowInTimePicker = {},
            onShowUntilTimePicker = {}
        )
    }
}