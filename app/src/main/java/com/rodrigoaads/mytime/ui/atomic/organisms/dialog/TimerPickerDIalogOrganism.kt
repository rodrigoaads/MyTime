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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rodrigoaads.mytime.R
import com.rodrigoaads.mytime.ui.atomic.atoms.LoadingAtom
import com.rodrigoaads.mytime.ui.theme.Dimen
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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

    val cal = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = selectedHour ?: cal.get(Calendar.HOUR_OF_DAY),
        initialMinute = selectedMinute ?: cal.get(Calendar.MINUTE),
        is24Hour = true
    )

    val formatter = remember { SimpleDateFormat("HH:mm", Locale.getDefault()) }

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
                        cal.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                        cal.set(Calendar.MINUTE, timePickerState.minute)
                        cal.isLenient = false
                        onTimeSelected(formatter.format(cal.time))
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