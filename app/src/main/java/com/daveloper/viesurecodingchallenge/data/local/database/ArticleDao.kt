package com.daveloper.viesurecodingchallenge.data.local.database

import androidx.room.*
import com.daveloper.viesurecodingchallenge.domain.entities.Article


@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    suspend fun getArticleItems(): List<Article>

    @Query("SELECT * FROM article WHERE id = :id")
    suspend fun getArticleItem(id: Int): Article

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Article>)

    @Delete
    suspend fun deleteArticle(article: Article)
}