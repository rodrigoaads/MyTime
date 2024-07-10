package com.rodrigoaads.mytime.data

import com.rodrigoaads.data.datasource.AppDataSource
import com.rodrigoaads.mytime.domain.entity.ActionState
import com.rodrigoaads.mytime.domain.entity.ItemModel
import com.rodrigoaads.mytime.extension.capitalizeEachWord
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

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
        val dateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val formatter = DateTimeFormatter.ofPattern("EEE dd MMM yyyy")
        return formatter.format(dateTime.toJavaLocalDateTime()).capitalizeEachWord()
    }
}