package com.daveloper.viesurecodingchallenge.data.local.database

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class LocalDateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long): LocalDate {
        return Instant.ofEpochMilli(value).atZone(ZoneId.systemDefault()).toLocalDate()
    }

    @TypeConverter
    fun toTimestamp(localDate: LocalDate): Long {
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }
}