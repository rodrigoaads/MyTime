package com.rodrigoaads.mytime.ui.navigation

sealed class MyTimeDestination(val route: String) {
    abstract fun createRoute(): String

    data object Time : MyTimeDestination("TIME_PAGE") {
        override fun createRoute() = route
    }

    data class Register(val id: String? = null) : MyTimeDestination("REGISTER_PAGE") {
        override fun createRoute() = "$route/$id"
    }
}