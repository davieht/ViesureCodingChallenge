package com.daveloper.viesurecodingchallenge.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.customFormat(): String {
    return this.format(
        DateTimeFormatter.ofPattern("E, MMM d, ''yy")
    )
}