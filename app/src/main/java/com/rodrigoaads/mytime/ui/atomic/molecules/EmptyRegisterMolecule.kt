package com.rodrigoaads.mytime.ui.atomic.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.R
import com.rodrigoaads.mytime.ui.atomic.atoms.BaseTextAtom
import com.rodrigoaads.mytime.ui.theme.Dimen
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun EmptyRegisterMolecule(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(Dimen.mediumIconSize),
            imageVector = Icons.Default.Info,
            contentDescription = null
        )
        Spacer(
            modifier = Modifier
                .height(Dimen.smallPadding)
        )
        BaseTextAtom(
            text = stringResource(id = R.string.empty_register_list),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MyTimeTheme {
        EmptyRegisterMolecule()
    }
}