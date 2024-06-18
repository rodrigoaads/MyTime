package com.rodrigoaads.mytime.ui.atomic.atoms

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun ErrorTextAtom(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.error
    )
}

@Preview
@Composable
private fun Preview() {
    MyTimeTheme {
        ErrorTextAtom(text = "Campo invalido.")
    }
}