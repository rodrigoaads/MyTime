package com.rodrigoaads.mytime.ui.atomic.organisms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.ui.atomic.molecules.EmptyRegisterMolecule
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun EmptyRegisterOrganism(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        EmptyRegisterMolecule()
    }
}

@Preview
@Composable
private fun Preview() {
    MyTimeTheme {
        EmptyRegisterOrganism()
    }
}