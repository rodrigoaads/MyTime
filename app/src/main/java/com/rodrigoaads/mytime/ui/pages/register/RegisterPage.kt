package com.rodrigoaads.mytime.ui.pages.register

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.rodrigoaads.mytime.R
import com.rodrigoaads.mytime.ui.atomic.molecules.AppBarMolecule
import com.rodrigoaads.mytime.ui.atomic.organisms.dialog.ActionDeleteDialogOrganism
import com.rodrigoaads.mytime.ui.atomic.templates.RegisterTemplate
import com.rodrigoaads.mytime.ui.pages.register.viewmodel.RegisterViewModel
import com.rodrigoaads.mytime.ui.pages.register.viewmodel.state.RegisterState
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterPage(
    id: String,
    navController: NavController,
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = koinViewModel(),
) {

    val uiState by registerViewModel.uiState.collectAsState()

    val context = LocalContext.current

    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = Unit) {
        if (id.isNotEmpty()) registerViewModel.getItemById(id)
    }

    LaunchedEffect(key1 = registerViewModel) {
        registerViewModel.state.collectLatest { state ->
            when(state) {
                is RegisterState.Error -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.error_text),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                RegisterState.SuccessAction -> {
                    navController.popBackStack()
                }
            }
        }
    }

    if (uiState.showDeleteDialog) {
        ActionDeleteDialogOrganism(
            onDismissRequest = {
                registerViewModel.manageDeleteDialog(false)
            },
            onConfirmation = {
                registerViewModel.manageDeleteDialog(false)
                registerViewModel.deleteItem()
            },
            dialogText = stringResource(id = R.string.action_delete_text)
        )
    }

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
            name = uiState.name,
            url = uiState.actionUrl,
            onNameChange = { text ->
                registerViewModel.changeNameText(text)
            },
            onUrlChange = { text ->
                registerViewModel.changeActionUrlText(text)
            },
            isEdit = id.isNotEmpty(),
            onClickRegisterOrEdit = {
                keyboardController?.hide()
                if (id.isNotEmpty()) registerViewModel.updateNameAndActionUseCase()
                    else registerViewModel.createItem()
            },
            onClickRemove = {
                keyboardController?.hide()
                registerViewModel.manageDeleteDialog(true)
            },
            isLoading = uiState.isLoading
        )
    }
}