package com.rodrigoaads.data.datasource

import com.rodrigoaads.data.model.TimeModel
import kotlinx.coroutines.flow.Flow

interface AppDataSource {
    fun getList(): Flow<List<TimeModel>>
    suspend fun createItem(
        name: String,
        actionUrl: String
    )
    suspend fun getItemById(id: String): TimeModel
    suspend fun updateNameAndAction(
        id: String,
        name: String,
        actionUrl: String
    )
    suspend fun deleteItem(id: String)
    suspend fun changeTimeIn(
        id: String,
        timeIn: String
    )
    suspend fun changeTimeUntil(
        id: String,
        timeUntil: String
    )
}