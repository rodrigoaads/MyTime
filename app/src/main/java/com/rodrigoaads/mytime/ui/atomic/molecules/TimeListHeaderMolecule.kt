package com.rodrigoaads.mytime.ui.atomic.molecules

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.ui.atomic.atoms.BaseTextAtom
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun TimeListHeaderMolecule(
    date: String,
    totalTime: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        BaseTextAtom(
            text = date,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.weight(1f))
        BaseTextAtom(
            text = totalTime,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MyTimeTheme {
        TimeListHeaderMolecule(
            date = "Qua, 30/05/2024",
            totalTime = "8h"
        )
    }
}