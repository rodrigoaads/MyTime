package com.rodrigoaads.mytime.ui.atomic.atoms

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.extensions.getHourOrMinute
import com.rodrigoaads.mytime.ui.atomic.organisms.dialog.TimePickerDialogOrganism
import com.rodrigoaads.mytime.ui.theme.Dimen
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun TimeTextFieldAtom(
    placeholder: String,
    time: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {

    var showTimerPicker by remember {
        mutableStateOf(false)
    }

    TimePickerDialogOrganism(
        show = showTimerPicker,
        onDismiss = { showTimerPicker = false },
        selectedHour = time.getHourOrMinute(),
        selectedMinute = time.getHourOrMinute(false)
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
                            showTimerPicker = true
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
            time = ""
        )
    }
}