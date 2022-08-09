package com.daveloper.viesurecodingchallenge.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Article(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val author: String,
    val releaseDate: LocalDate,
    val image: String,
)