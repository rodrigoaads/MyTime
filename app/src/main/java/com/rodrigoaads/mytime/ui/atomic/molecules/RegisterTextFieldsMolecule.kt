package com.rodrigoaads.mytime.ui.atomic.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.R
import com.rodrigoaads.mytime.ui.atomic.atoms.BaseTextAtom
import com.rodrigoaads.mytime.ui.atomic.atoms.BaseTextFieldAtom
import com.rodrigoaads.mytime.ui.theme.Dimen
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun RegisterTextFieldsMolecule(
    name: String,
    url: String,
    onNameChange: (String) -> Unit,
    onUrlChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        BaseTextFieldAtom(
            label = stringResource(id = R.string.register_name),
            text = name,
            onTextChange = onNameChange,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )
        Spacer(
            modifier = Modifier
                .height(Dimen.smallPadding)
        )
        BaseTextFieldAtom(
            label = stringResource(id = R.string.register_url),
            text = url,
            onTextChange = onUrlChange,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            )
        )
        Spacer(
            modifier = Modifier
                .height(Dimen.smallPadding)
        )
        BaseTextAtom(
            text = stringResource(id = R.string.register_url_tip)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MyTimeTheme {
        RegisterTextFieldsMolecule(
            name = "",
            url = "",
            onNameChange = {},
            onUrlChange = {}
        )
    }
}