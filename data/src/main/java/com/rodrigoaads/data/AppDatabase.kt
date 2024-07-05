package com.rodrigoaads.data

import com.rodrigoaads.data.model.TimeDbModel
import com.rodrigoaads.data.model.TimeModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import org.mongodb.kbson.ObjectId

class AppDatabase(
    private val realm: Realm
) {
    fun getList() = realm.query<TimeDbModel>().find().asFlow()

    suspend fun createItem(item: TimeModel) {
        realm.write {
            copyToRealm(
                TimeDbModel().apply {
                    name = item.name
                    timeIn = item.timeIn
                    timeUntil = item.timeUntil
                    actionUrl = item.actionUrl
                }
            )
        }
    }

    suspend fun getItemById(id: ObjectId): TimeDbModel {
        return realm.write {
            query<TimeDbModel>(QUERY_BY_ID, id).find().first()
        }
    }

    suspend fun updateNameAndAction(item: TimeModel) {
        realm.write {
            val dbItem = query<TimeDbModel>(QUERY_BY_ID, ObjectId(hexString = item.id)).find().first()
            dbItem.name = item.name
            dbItem.actionUrl = item.actionUrl
        }
    }

    suspend fun deleteItem(id: ObjectId) {
        realm.write {
            val itemToDelete = query<TimeDbModel>(QUERY_BY_ID, id).find().first()
            delete(itemToDelete)
        }
    }

    companion object {
        private const val QUERY_BY_ID = "_id == $0"
    }
}