package com.rodrigoaads.mytime.ui.atomic.organisms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.domain.entity.TimeItemModel
import com.rodrigoaads.mytime.ui.atomic.molecules.TimeItemMolecule
import com.rodrigoaads.mytime.ui.theme.Dimen
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun TimeOrganism(
    timeList: List<TimeItemModel>,
    onTimeInChange: (Int, String) -> Unit,
    onTimeUntilChange: (Int, String) -> Unit,
    onClickAction: ((String) -> Unit)?,
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
                )
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
                TimeItemModel(
                    id = 0,
                    name = "Teste 1",
                    calculatingTime = "1h",
                    timeIn = "14:00",
                    timeUntil = "15:00",
                    showError = false,
                    actionUrl = ""
                ),
                TimeItemModel(
                    id = 1,
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
            onClickAction = {}
        )
    }
}