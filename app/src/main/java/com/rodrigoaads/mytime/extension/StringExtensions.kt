package com.rodrigoaads.mytime.extension

import com.rodrigoaads.mytime.constants.StringConstants

fun String.getHourOrMinute(isHour: Boolean = true): Int? {
    return if (this.isNotEmpty()) {
        try {
            if (this == StringConstants.INITIAL_TIME) return null
            this.split(":")[if (isHour) 0 else 1].toInt()
        } catch (e: Exception) {
            null
        }
    } else null
}

fun String.getInterval(): Int? {
    return try {
        this.split(":").let {
            (it[0] + it[1]).toInt()
        }
    }catch (e: Exception) {
        null
    }
}

fun String.capitalizeEachWord(): String {
    val regex = "(\\b[a-z](?!\\s))".toRegex()
    return this.replace(regex) { it.value.uppercase() }
}