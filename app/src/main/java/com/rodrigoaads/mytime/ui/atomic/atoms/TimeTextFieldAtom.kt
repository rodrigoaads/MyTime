package com.rodrigoaads.mytime.ui.atomic.atoms

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.extension.getHourOrMinute
import com.rodrigoaads.mytime.ui.atomic.organisms.dialog.TimePickerDialogOrganism
import com.rodrigoaads.mytime.ui.theme.Dimen
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun TimeTextFieldAtom(
    placeholder: String,
    time: String,
    showTimerPicker: Boolean,
    isChangeTimeLoading: Boolean,
    onTextChange: (String) -> Unit,
    onDismissTimePicker: () -> Unit,
    onShowTimePicker: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {

    TimePickerDialogOrganism(
        show = showTimerPicker,
        onDismiss = onDismissTimePicker,
        selectedHour = time.getHourOrMinute(),
        selectedMinute = time.getHourOrMinute(false),
        isChangeTimeLoading = isChangeTimeLoading
    ) { selectedTime ->
        onTextChange(selectedTime)
    }


    TextField(
        modifier = modifier
            .width(Dimen.timeTextFieldWidth),
        value = time,
        onValueChange = {},
        placeholder = {
            Text(text = placeholder)
        },
        readOnly = true,
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() }
            .also { source ->
                LaunchedEffect(source) {
                    source.interactions.collect {
                        if (it is PressInteraction.Release) {
                            onShowTimePicker.invoke()
                        }
                    }
                }
            }
    )
}

@Preview
@Composable
private fun Preview() {
    MyTimeTheme {
        TimeTextFieldAtom(
            placeholder = "Text here",
            onTextChange = {},
            time = "",
            isChangeTimeLoading = false,
            showTimerPicker = false,
            onDismissTimePicker = {},
            onShowTimePicker = {}
        )
    }
}