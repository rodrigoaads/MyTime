package com.rodrigoaads.mytime.ui.atomic.organisms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.ui.atomic.atoms.LoadingAtom
import com.rodrigoaads.mytime.ui.theme.Dimen
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun LoadingOrganism(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        LoadingAtom(
            modifier = Modifier
                .align(Alignment.Center),
            strokeWidth = Dimen.loadingStrokeWidth
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MyTimeTheme {
        LoadingOrganism()
    }
}