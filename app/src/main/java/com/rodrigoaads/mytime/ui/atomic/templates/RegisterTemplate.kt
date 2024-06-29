package com.rodrigoaads.mytime.ui.atomic.templates

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoaads.mytime.ui.atomic.organisms.RegisterOrganism
import com.rodrigoaads.mytime.ui.theme.Dimen
import com.rodrigoaads.mytime.ui.theme.MyTimeTheme

@Composable
fun RegisterTemplate(
    name: String,
    url: String,
    onNameChange: (String) -> Unit,
    onUrlChange: (String) -> Unit,
    isEdit: Boolean,
    onClickRegisterOrEdit: () -> Unit,
    onClickRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimen.largePadding)
            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {
        RegisterOrganism(
            modifier = Modifier
                .align(Alignment.Center),
            name = name,
            url = url,
            onNameChange = onNameChange,
            onUrlChange = onUrlChange,
            isEdit = isEdit,
            onClickRegisterOrEdit = onClickRegisterOrEdit,
            onClickRemove = onClickRemove
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MyTimeTheme {
        RegisterTemplate(
            name = "",
            url = "",
            onNameChange = {},
            onUrlChange = {},
            isEdit = false,
            onClickRegisterOrEdit = {},
            onClickRemove = {}
        )
    }
}