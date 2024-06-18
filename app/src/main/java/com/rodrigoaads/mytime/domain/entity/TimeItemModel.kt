package com.rodrigoaads.mytime.domain.entity

data class TimeItemModel(
    val id: Int,
    val name: String,
    val calculatingTime: String,
    val timeIn: String,
    val timeUntil: String,
    val showError: Boolean,
    val actionUrl: String
)
