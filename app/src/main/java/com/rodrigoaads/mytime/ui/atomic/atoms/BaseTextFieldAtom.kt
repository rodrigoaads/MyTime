package com.rodrigoaads.mytime.ui.atomic.atoms

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun BaseTextFieldAtom(
    label: String,
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = false
) {
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = onTextChange,
        label = {
            Text(text = label)
        },
        singleLine = singleLine
    )
}

@Preview
@Composable
private fun Preview() {
    MyTimeTheme {
        BaseTextFieldAtom(
            label = "Text here",
            text = "",
            onTextChange = {}
        )
    }
}