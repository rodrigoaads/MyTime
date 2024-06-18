package com.rodrigoaads.mytime.ui.atomic.molecules

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.R
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarMolecule(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.app_name),
    showNavigationIcon: Boolean = true,
    onClickNavigation: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = text)
        },
        navigationIcon = {
            if (showNavigationIcon) {
                IconButton(
                    onClick = onClickNavigation
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    MyTimeTheme {
        AppBarMolecule()
    }
}