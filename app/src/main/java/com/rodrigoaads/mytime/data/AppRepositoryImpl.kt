package com.rodrigoaads.mytime.data

import com.rodrigoaads.data.datasource.AppDataSource
import com.rodrigoaads.mytime.domain.entity.ActionState
import com.rodrigoaads.mytime.domain.entity.ItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class AppRepositoryImpl(
    private val appDataSource: AppDataSource
): AppRepository {
    override fun getList(): Flow<List<ItemModel>> {
        return appDataSource.getList().map { result ->
            result.map { item ->
                ItemModel(
                    id = item.id,
                    name = item.name,
                    timeIn = item.timeIn,
                    timeUntil = item.timeUntil,
                    actionUrl = item.actionUrl
                )
            }
        }
    }

    override suspend fun createItem(
        name: String,
        actionUrl: String
    ): ActionState<Nothing> {
        return try {
            appDataSource.createItem(
                name = name,
                actionUrl = actionUrl
            )
            ActionState.Success()
        }catch (e: Exception) {
            ActionState.Error(e.message)
        }
    }

    override suspend fun getItemById(id: String): ActionState<ItemModel> {
        return try {
            val item = appDataSource.getItemById(id).let { model ->
                ItemModel(
                    id = model.id,
                    name = model.name,
                    timeIn = model.timeIn,
                    timeUntil = model.timeUntil,
                    actionUrl = model.actionUrl
                )
            }
            ActionState.Success(item)
        }catch (e: Exception) {
            ActionState.Error(e.message)
        }
    }

    override suspend fun updateNameAndAction(
        id: String,
        name: String,
        actionUrl: String
    ): ActionState<Nothing> {
        return try {
            appDataSource.updateNameAndAction(
                id = id,
                name = name,
                actionUrl = actionUrl
            )
            ActionState.Success()
        }catch (e: Exception) {
            ActionState.Error(e.message)
        }
    }

    override suspend fun deleteItem(id: String): ActionState<Nothing> {
        return try {
            appDataSource.deleteItem(id)
            ActionState.Success()
        }catch (e: Exception) {
            ActionState.Error(e.message)
        }
    }

    override suspend fun changeTimeIn(
        id: String,
        timeIn: String
    ): ActionState<Nothing> {
        return try {
            appDataSource.changeTimeIn(
                id = id,
                timeIn = timeIn
            )
            ActionState.Success()
        }catch (e: Exception) {
            ActionState.Error(e.message)
        }
    }

    override suspend fun changeTimeUntil(
        id: String,
        timeUntil: String
    ): ActionState<Nothing> {
        return try {
            appDataSource.changeTimeUntil(
                id = id,
               timeUntil = timeUntil
            )
            ActionState.Success()
        }catch (e: Exception) {
            ActionState.Error(e.message)
        }
    }

    override fun getDate(): String {
        val now = Clock.System.now()
        val dateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())

        val formattedDate = buildString {
            append(dateTime.dayOfWeek.name.substring(0,3).lowercase().replaceFirstChar { it.uppercase() })
            append(", ")
            append(dateTime.dayOfMonth.toString().padStart(2, '0'))
            append(" ")
            append(dateTime.month.name.substring(0, 3).lowercase().replaceFirstChar { it.uppercase() })
            append(" ")
            append(dateTime.year)
        }
        return formattedDate
    }
}