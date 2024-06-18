package com.rodrigoaads.mytime.ui.atomic.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.R
import com.rodrigoaads.mytime.ui.atomic.atoms.BaseTextAtom
import com.rodrigoaads.mytime.ui.atomic.atoms.ErrorTextAtom
import com.rodrigoaads.mytime.ui.atomic.atoms.ItemActionIconAtom
import com.rodrigoaads.mytime.ui.atomic.atoms.ItemTitleAtom
import com.rodrigoaads.mytime.ui.atomic.atoms.TimeTextFieldAtom
import com.rodrigoaads.mytime.ui.theme.Dimen
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun TimeItemMolecule(
    text: String,
    calculatingTime: String,
    timeIn: String,
    timeUntil: String,
    showError: Boolean,
    onTimeInChange: (String) -> Unit,
    onTimeUntilChange: (String) -> Unit,
    onClickAction: Pair<Boolean, (() -> Unit)?>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(Dimen.cardCornerRadius)
    ) {
        Column(
            modifier = modifier
                .padding(Dimen.mediumPadding)
        ) {
            ItemTitleAtom(
                text = text
            )
            Row {
                TimeTextFieldAtom(
                    placeholder = stringResource(id = R.string.time_in),
                    onTextChange = onTimeInChange,
                    time = timeIn,
                )
                BaseTextAtom(
                    modifier = Modifier
                        .align(Alignment.Bottom),
                    text = stringResource(id = R.string.time_separator)
                )
                TimeTextFieldAtom(
                    placeholder = stringResource(id = R.string.time_until),
                    onTextChange = onTimeUntilChange,
                    time = timeUntil,
                    enabled = timeIn.isNotEmpty()
                )
                Spacer(modifier = Modifier.weight(1f))
                BaseTextAtom(
                    modifier = Modifier
                        .align(Alignment.Bottom),
                    text = calculatingTime
                )
                if (onClickAction.first) {
                    Spacer(
                        modifier = Modifier.width(Dimen.largePadding)
                    )
                    ItemActionIconAtom(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    ) {
                        onClickAction.second?.invoke()
                    }
                }
            }
            if (showError) {
                Spacer(
                    modifier = Modifier
                        .padding(top = Dimen.smallPadding)
                )
                ErrorTextAtom(
                    text = stringResource(id = R.string.invalid_range)
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MyTimeTheme {
        TimeItemMolecule(
            text = "Tarefa X",
            calculatingTime = "0h",
            onTimeInChange = {},
            onTimeUntilChange = {},
            timeIn = "",
            timeUntil = "",
            showError = false,
            onClickAction = Pair(
                first = true,
                second = {}
            )
        )
    }
}