package com.rodrigoaads.mytime.domain.entity

sealed class ActionState<out T>{
    data class Success<T>(val item: T? = null) : ActionState<T>()
    data class Error(val message: String?): ActionState<Nothing>()
}