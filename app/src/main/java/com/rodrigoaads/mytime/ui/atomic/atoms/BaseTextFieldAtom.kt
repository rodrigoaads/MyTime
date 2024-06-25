package com.rodrigoaads.mytime.ui.atomic.atoms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = false
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = onTextChange,
        label = {
            Text(text = label)
        },
        singleLine = singleLine,
        keyboardOptions = keyboardOptions
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