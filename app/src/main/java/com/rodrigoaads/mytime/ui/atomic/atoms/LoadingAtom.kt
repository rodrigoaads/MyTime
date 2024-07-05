package com.rodrigoaads.mytime.ui.atomic.atoms

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.rodrigoaads.mytime.ui.theme.Dimen
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun LoadingAtom(
    strokeWidth: Dp,
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier = modifier,
        strokeWidth = strokeWidth
    )
}

@Preview
@Composable
private fun Preview() {
    MyTimeTheme {
        LoadingAtom(strokeWidth = Dimen.buttonLoadingStrokeWidth)
    }
}