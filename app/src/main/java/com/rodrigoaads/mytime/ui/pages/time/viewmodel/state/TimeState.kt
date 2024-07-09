package com.rodrigoaads.mytime.ui.pages.time.viewmodel.state

sealed class TimeState {
    data class Error(val message: String): TimeState()
}