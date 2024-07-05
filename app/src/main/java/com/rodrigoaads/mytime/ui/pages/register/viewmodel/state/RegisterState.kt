package com.rodrigoaads.mytime.ui.pages.register.viewmodel.state

sealed class RegisterState {
    data class Error(val message: String): RegisterState()
    data object SuccessAction: RegisterState()
}