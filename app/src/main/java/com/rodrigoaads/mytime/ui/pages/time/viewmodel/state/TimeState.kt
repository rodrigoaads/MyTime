package com.rodrigoaads.mytime.ui.pages.time.viewmodel.state

sealed class TimeState {
    data object Error: TimeState()
}