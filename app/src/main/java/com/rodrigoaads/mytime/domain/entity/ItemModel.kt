package com.rodrigoaads.mytime.domain.entity

data class ItemModel(
    val id: String,
    val name: String,
    val calculatingTime: String = "",
    val timeIn: String,
    val timeUntil: String,
    val showError: Boolean = false,
    val actionUrl: String
)
