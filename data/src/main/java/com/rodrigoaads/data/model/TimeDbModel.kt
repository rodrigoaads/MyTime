package com.rodrigoaads.data.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class TimeDbModel: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var timeIn: String = ""
    var timeUntil: String = ""
    var actionUrl: String = ""
}