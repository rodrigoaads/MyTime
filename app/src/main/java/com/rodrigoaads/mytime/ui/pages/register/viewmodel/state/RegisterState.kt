package com.rodrigoaads.mytime.ui.pages.register.viewmodel.state

sealed class RegisterState {
    data object Error: RegisterState()
    data object SuccessAction: RegisterState()
}