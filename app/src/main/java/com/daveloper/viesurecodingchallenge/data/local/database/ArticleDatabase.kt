package com.daveloper.viesurecodingchallenge.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.daveloper.viesurecodingchallenge.domain.entities.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(LocalDateConverter::class)
abstract class ArticleDatabase: RoomDatabase() {
    abstract val articleDao: ArticleDao
}