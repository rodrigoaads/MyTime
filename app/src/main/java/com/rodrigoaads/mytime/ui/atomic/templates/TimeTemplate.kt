package com.rodrigoaads.mytime.ui.atomic.templates

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.domain.entity.ItemModel
import com.rodrigoaads.mytime.ui.atomic.atoms.AddButtonAtom
import com.rodrigoaads.mytime.ui.atomic.molecules.TimeListHeaderMolecule
import com.rodrigoaads.mytime.ui.atomic.organisms.EmptyRegisterOrganism
import com.rodrigoaads.mytime.ui.atomic.organisms.LoadingOrganism
import com.rodrigoaads.mytime.ui.atomic.organisms.TimeOrganism
import com.rodrigoaads.mytime.ui.theme.Dimen
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun TimeTemplate(
    date: String,
    totalTime: String,
    timeList: List<ItemModel>,
    onTimeInChange: (String, String) -> Unit,
    onTimeUntilChange: (String, String) -> Unit,
    onClickAdd: () -> Unit,
    onClickAction: ((String) -> Unit)?,
    onClickCard: (String) -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimen.largePadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (isLoading) {
                LoadingOrganism(
                    withText = true
                )
            } else {
                TimeListHeaderMolecule(
                    date = date,
                    totalTime = totalTime
                )
                Spacer(
                    modifier = Modifier
                        .height(Dimen.smallPadding)
                )
                if (timeList.isNotEmpty()) {
                    TimeOrganism(
                        timeList = timeList,
                        onTimeInChange = onTimeInChange,
                        onTimeUntilChange = onTimeUntilChange,
                        onClickAction = onClickAction,
                        onClickCard = onClickCard
                    )
                } else {
                    EmptyRegisterOrganism()
                }
            }
        }
        AddButtonAtom(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(Dimen.smallPadding)
        ){
            onClickAdd.invoke()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MyTimeTheme {
        TimeTemplate(
            date = "Qua, 30/05/2024",
            totalTime = "8h",
            timeList = listOf(
                ItemModel(
                    id = "0",
                    name = "Teste 1",
                    calculatingTime = "1h",
                    timeIn = "14:00",
                    timeUntil = "15:00",
                    showError = false,
                    actionUrl = "abc"
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
            onClickAdd = {},
            onClickAction = {},
            onClickCard = {},
            isLoading = false
        )
    }
}