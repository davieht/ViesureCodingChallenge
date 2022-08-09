package com.daveloper.viesurecodingchallenge.domain

import com.daveloper.viesurecodingchallenge.data.local.database.Article
import com.daveloper.viesurecodingchallenge.data.util.Resource

interface ArticleRepository {
    suspend fun getArticles(): Resource<List<Article>>
    suspend fun getArticle(id: Int): Article
}