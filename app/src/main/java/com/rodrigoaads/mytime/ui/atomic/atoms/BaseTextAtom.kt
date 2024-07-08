package com.rodrigoaads.mytime.ui.atomic.atoms

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun BaseTextAtom(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    textAlign: TextAlign = TextAlign.Unspecified
) {
    Text(
        modifier = modifier,
        text = text,
        style = style,
        textAlign = textAlign
    )
}

@Preview
@Composable
private fun Preview() {
    MyTimeTheme {
        BaseTextAtom(text = "Text here")
    }
}