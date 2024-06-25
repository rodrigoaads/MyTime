package com.rodrigoaads.mytime.ui.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.rodrigoaads.mytime.R
import com.rodrigoaads.mytime.ui.atomic.molecules.AppBarMolecule
import com.rodrigoaads.mytime.ui.atomic.templates.RegisterTemplate

@Composable
fun RegisterPage(
    id: Int,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            AppBarMolecule(
                text = stringResource(R.string.register_navigation_title),
                onClickNavigation = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValue ->
        RegisterTemplate(
            modifier = modifier
                .padding(paddingValue),
            name = "$id",
            url = "",
            onNameChange = {},
            onUrlChange = {},
            isEdit = false,
            onClickRegisterOrEdit = {},
            onClickRemove = {}
        )
    }
}