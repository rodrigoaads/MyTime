package com.rodrigoaads.mytime.ui.pages.time

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.rodrigoaads.mytime.ui.atomic.molecules.AppBarMolecule
import com.rodrigoaads.mytime.ui.atomic.templates.TimeTemplate
import com.rodrigoaads.mytime.ui.navigation.MyTimeDestination
import com.rodrigoaads.mytime.ui.pages.time.viewmodel.TimeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TimePage(
    navController: NavController,
    modifier: Modifier = Modifier,
    timeViewModel: TimeViewModel = koinViewModel(),
) {

    val uiState by timeViewModel.uiState.collectAsState()

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
            timeList = uiState.list ?: listOf(),
            onTimeInChange = { _, _ -> },
            onTimeUntilChange = { _, _ -> },
            onClickAdd = {
                navController.navigate(MyTimeDestination.Register(null).createRoute())
            },
            onClickAction = {},
            onClickCard = { id ->
                navController.navigate(MyTimeDestination.Register(id).createRoute())
            },
            isLoading = uiState.list == null
        )
    }
}