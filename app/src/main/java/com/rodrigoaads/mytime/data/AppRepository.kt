package com.rodrigoaads.mytime.data

import com.rodrigoaads.mytime.domain.entity.ActionState
import com.rodrigoaads.mytime.domain.entity.ItemModel
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getList(): Flow<List<ItemModel>>
    suspend fun createItem(
        name: String,
        actionUrl: String
    ): ActionState<Nothing>
    suspend fun getItemById(id: String): ActionState<ItemModel>
    suspend fun updateNameAndAction(
        id: String,
        name: String,
        actionUrl: String
    ): ActionState<Nothing>
    suspend fun deleteItem(id: String): ActionState<Nothing>
}