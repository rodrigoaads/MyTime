package com.rodrigoaads.mytime.ui.atomic.organisms.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rodrigoaads.mytime.R
import com.rodrigoaads.mytime.ui.atomic.atoms.LoadingAtom
import com.rodrigoaads.mytime.ui.theme.Dimen
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialogOrganism(
    show: Boolean,
    selectedHour: Int?,
    selectedMinute: Int?,
    isChangeTimeLoading: Boolean,
    onDismiss: () -> Unit,
    onTimeSelected: (String) -> Unit
) {

    val currentTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    val timePickerState = rememberTimePickerState(
        initialHour = selectedHour ?: currentTime.hour,
        initialMinute = selectedMinute ?: currentTime.minute,
        is24Hour = true
    )

    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(text = stringResource(id = R.string.time_picker_title))
            },
            text = {
                Column {
                    TimePicker(state = timePickerState)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val time = LocalDateTime(
                            year = currentTime.year,
                            monthNumber = currentTime.monthNumber,
                            dayOfMonth = currentTime.dayOfMonth,
                            hour = timePickerState.hour,
                            minute = timePickerState.minute,
                        )
                        val formatter = DateTimeFormatter.ofPattern("HH:mm")
                        onTimeSelected(formatter.format(time.toJavaLocalDateTime()))
                    },
                    enabled = !isChangeTimeLoading
                ) {
                    if (isChangeTimeLoading) {
                        LoadingAtom(
                            modifier = Modifier
                                .size(Dimen.buttonLoadingSize),
                            strokeWidth = Dimen.buttonLoadingStrokeWidth
                        )
                    } else {
                        Text(text = stringResource(id = R.string.ok))
                    }
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = onDismiss
                ) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            }
        )
    }
}