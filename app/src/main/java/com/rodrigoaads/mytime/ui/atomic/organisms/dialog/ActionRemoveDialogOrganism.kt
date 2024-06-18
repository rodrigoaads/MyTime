package com.rodrigoaads.mytime.ui.atomic.organisms.dialog

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.R
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun ActionRemoveDialogOrganism(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogText: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Rounded.Warning,
) {
    AlertDialog(
        modifier = modifier,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
        },
        title = {
                Text(text = stringResource(id = R.string.attention))
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            Button(
                onClick = onConfirmation,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text(
                    text = stringResource(id = R.string.confirm),
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = onDismissRequest
            ) {
                Text(text = stringResource(id = R.string.not_now))
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    MyTimeTheme {
        ActionRemoveDialogOrganism(
            onDismissRequest = {},
            onConfirmation = {},
            dialogText = "Deseja realmente sair?"
        )
    }
}

@Preview
@Composable
private fun PreviewWithDarkTheme() {
    MyTimeTheme(darkTheme = true) {
        ActionRemoveDialogOrganism(
            onDismissRequest = {},
            onConfirmation = {},
            dialogText = "Deseja realmente sair?"
        )
    }
}