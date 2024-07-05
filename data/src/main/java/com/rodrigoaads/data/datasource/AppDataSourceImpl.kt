package com.rodrigoaads.data.datasource

import com.rodrigoaads.data.AppDatabase
import com.rodrigoaads.data.model.TimeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId

class AppDataSourceImpl(
    private val appDatabase: AppDatabase
): AppDataSource {
    override fun getList(): Flow<List<TimeModel>> {
        return appDatabase.getList().map { result ->
            result.list.map { model ->
                TimeModel(
                    id = model._id.toHexString(),
                    name = model.name,
                    timeIn = model.timeIn,
                    timeUntil = model.timeUntil,
                    actionUrl = model.actionUrl
                )
            }
        }
    }

    override suspend fun createItem(
        name: String,
        actionUrl: String
    ) = appDatabase.createItem(
        TimeModel(
            name = name,
            actionUrl = actionUrl
        )
    )

    override suspend fun getItemById(id: String): TimeModel {
        return appDatabase.getItemById(ObjectId(hexString = id)).let { model ->
            TimeModel(
                id = model._id.toHexString(),
                name = model.name,
                timeIn = model.timeIn,
                timeUntil = model.timeUntil,
                actionUrl = model.actionUrl
            )
        }
    }

    override suspend fun updateNameAndAction(
        id: String,
        name: String,
        actionUrl: String
    ) = appDatabase.updateNameAndAction(
        TimeModel(
            id = id,
            name = name,
            actionUrl = actionUrl
        )
    )

    override suspend fun deleteItem(id: String) = appDatabase.deleteItem(ObjectId(hexString = id))
}