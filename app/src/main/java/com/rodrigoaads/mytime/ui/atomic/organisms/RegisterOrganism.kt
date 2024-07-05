package com.rodrigoaads.mytime.ui.atomic.organisms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.R
import com.rodrigoaads.mytime.ui.atomic.atoms.BaseTextAtom
import com.rodrigoaads.mytime.ui.atomic.atoms.LoadingAtom
import com.rodrigoaads.mytime.ui.atomic.molecules.RegisterTextFieldsMolecule
import com.rodrigoaads.mytime.ui.theme.Dimen
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun RegisterOrganism(
    name: String,
    url: String,
    onNameChange: (String) -> Unit,
    onUrlChange: (String) -> Unit,
    isEdit: Boolean,
    isLoading: Boolean,
    onClickRegisterOrEdit: () -> Unit,
    onClickRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        BaseTextAtom(
            text = stringResource(
                id = if (isEdit) R.string.edit_register_title
                else R.string.register_title
            )
        )
        Spacer(
            modifier = Modifier
                .height(Dimen.mediumPadding)
        )
        RegisterTextFieldsMolecule(
            name = name,
            url = url,
            onNameChange = onNameChange,
            onUrlChange = onUrlChange
        )
        Spacer(
            modifier = Modifier
                .height(Dimen.smallPadding)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = onClickRegisterOrEdit,
            enabled = !isLoading
        ) {
            if (isLoading) {
                LoadingAtom(
                    modifier = Modifier
                        .size(Dimen.buttonLoadingSize),
                    strokeWidth = Dimen.buttonLoadingStrokeWidth
                )
            } else {
                Text(
                    text = stringResource(
                        id = if (isEdit) R.string.edit_button
                        else R.string.register_button
                    )
                )
            }
        }
        if (isEdit && !isLoading) {
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = onClickRemove,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                ),
                border = BorderStroke(
                    width = Dimen.outlinedButtonBorder,
                    color = MaterialTheme.colorScheme.error
                )
            ) {
                Text(
                    text = stringResource(id = R.string.remove_button)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    MyTimeTheme {
        RegisterOrganism(
            name = "",
            url = "",
            onNameChange = {},
            onUrlChange = {},
            isEdit = false,
            onClickRegisterOrEdit = {},
            onClickRemove = {},
            isLoading = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewWithRemove() {
    MyTimeTheme {
        RegisterOrganism(
            name = "",
            url = "",
            onNameChange = {},
            onUrlChange = {},
            isEdit = true,
            onClickRegisterOrEdit = {},
            onClickRemove = {},
            isLoading = false
        )
    }
}