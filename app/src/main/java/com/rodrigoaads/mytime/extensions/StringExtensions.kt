package com.rodrigoaads.mytime.extensions

fun String.getHourOrMinute(isHour: Boolean = true): Int? {
    return if (this.isNotEmpty()) {
        try {
            this.split(":")[if (isHour) 0 else 1].toInt()
        } catch (e: Exception) {
            null
        }
    } else null
}