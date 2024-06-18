package com.rodrigoaads.mytime.ui.atomic.atoms

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun AddButtonAtom(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
       modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MyTimeTheme {
        AddButtonAtom{}
    }
}