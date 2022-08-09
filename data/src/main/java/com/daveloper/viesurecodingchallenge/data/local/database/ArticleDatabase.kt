package com.daveloper.viesurecodingchallenge.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(LocalDateConverter::class)
abstract class ArticleDatabase: RoomDatabase() {
    abstract val articleDao: ArticleDao
}