package com.daveloper.viesurecodingchallenge.data.remote

import com.squareup.moshi.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateAdapter {

    @ToJson fun toJson(localDate: LocalDate): String {
        return localDate.toString()
    }

    @FromJson fun fromJson(s: String): LocalDate {
        return LocalDate.parse(s, DateTimeFormatter.ofPattern("M/d/yyyy"))
    }
}