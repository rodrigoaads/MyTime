package com.rodrigoaads.mytime.extension

fun String.getHourOrMinute(isHour: Boolean = true): Int? {
    return if (this.isNotEmpty()) {
        try {
            if (this == "00:00") return null
            this.split(":")[if (isHour) 0 else 1].toInt()
        } catch (e: Exception) {
            null
        }
    } else null
}