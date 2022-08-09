package com.daveloper.viesurecodingchallenge.data.remote

import com.daveloper.viesurecodingchallenge.data.local.database.Article
import com.squareup.moshi.Json
import java.time.LocalDate

data class ArticleDto(
    val id: Int,
    val title: String?,
    val description: String?,
    val author: String?,
    @Json(name = "release_date")
    val releaseDate: LocalDate,
    val image: String?,
)

fun ArticleDto.toArticle(): Article {
    return Article(
        id = id,
        title = title.orEmpty(),
        description = description.orEmpty(),
        author = author.orEmpty(),
        releaseDate = releaseDate,
        image = image.orEmpty()
            .replace("http", "https")
    )
}

fun List<ArticleDto>.toArticleList(): List<Article> {
    return this.map { it.toArticle() }
}