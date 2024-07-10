package com.rodrigoaads.mytime.ui.pages.time

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.rodrigoaads.mytime.R
import com.rodrigoaads.mytime.ui.atomic.molecules.AppBarMolecule
import com.rodrigoaads.mytime.ui.atomic.templates.TimeTemplate
import com.rodrigoaads.mytime.ui.navigation.MyTimeDestination
import com.rodrigoaads.mytime.ui.pages.time.viewmodel.TimeViewModel
import com.rodrigoaads.mytime.ui.pages.time.viewmodel.state.TimeState
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun TimePage(
    navController: NavController,
    modifier: Modifier = Modifier,
    timeViewModel: TimeViewModel = koinViewModel(),
) {

    val uiState by timeViewModel.uiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = timeViewModel) {
        timeViewModel.state.collectLatest { state ->
            when(state) {
                is TimeState.Error -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.error_text),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

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
            date = uiState.date,
            totalTime = uiState.totalTime,
            timeList = uiState.list ?: listOf(),
            onTimeInChange = { id, time ->
                timeViewModel.changeTimeIn(
                    id = id,
                    timeIn = time
                )
            },
            onTimeUntilChange = { id, time ->
                timeViewModel.changeTimeUntil(
                    id = id,
                    timeUntil = time
                )
            },
            onClickAdd = {
                navController.navigate(MyTimeDestination.Register(null).createRoute())
            },
            onClickAction = {},
            onClickCard = { id ->
                navController.navigate(MyTimeDestination.Register(id).createRoute())
            },
            isLoading = uiState.list == null,
            isChangeInTimeLoading = uiState.isChangeInTimeLoading,
            isChangeUntilTimeLoading = uiState.isChangeUntilTimeLoading,
            showInTimerPicker = uiState.showInTimePicker,
            showUntilTimerPicker = uiState.showUntilTimePicker,
            onDismissInTimePicker = {
                timeViewModel.manageInTimePicker(show = false)
            },
            onDismissUntilTimePicker = {
                timeViewModel.manageUntilTimePicker(show = false)
            },
            onShowInTimePicker = { id ->
                timeViewModel.manageInTimePicker(
                    id = id,
                    show = true
                )
            },
            onShowUntilTimePicker = { id ->
                timeViewModel.manageUntilTimePicker(
                    id = id,
                    show = true
                )
            }
        )
    }
}