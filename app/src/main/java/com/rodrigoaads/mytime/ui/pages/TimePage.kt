package com.rodrigoaads.mytime.ui.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.rodrigoaads.mytime.domain.entity.TimeItemModel
import com.rodrigoaads.mytime.ui.atomic.molecules.AppBarMolecule
import com.rodrigoaads.mytime.ui.atomic.templates.TimeTemplate
import com.rodrigoaads.mytime.ui.navigation.MyTimeDestination

@Composable
fun TimePage(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            AppBarMolecule(
                showNavigationIcon = false
            )
        }
    ) { paddingValue ->
        TimeTemplate(
            modifier = modifier
                .padding(paddingValue),
            date = "Qua, 30/05/2024",
            totalTime = "8h",
            timeList = listOf(
                TimeItemModel(
                    id = 1,
                    name = "Teste 1",
                    calculatingTime = "1h",
                    timeIn = "14:00",
                    timeUntil = "15:00",
                    showError = false,
                    actionUrl = "abc"
                ),
                TimeItemModel(
                    id = 2,
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
            onClickAdd = {
                navController.navigate(MyTimeDestination.Register(0).createRoute())
            },
            onClickAction = {},
            onClickCard = { id ->
                navController.navigate(MyTimeDestination.Register(id).createRoute())
            }
        )
    }
}