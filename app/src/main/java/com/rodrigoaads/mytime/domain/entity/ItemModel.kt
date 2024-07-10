package com.rodrigoaads.mytime.domain.entity

data class ItemModel(
    val id: String,
    val name: String,
    val timeIn: String,
    val timeUntil: String,
    val actionUrl: String
)
