package com.daveloper.viesurecodingchallenge.data.domain

import com.daveloper.viesurecodingchallenge.data.local.database.Article
import com.daveloper.viesurecodingchallenge.utils.Resource

interface ArticleRepository {
    suspend fun getArticles(): Resource<List<Article>>
    suspend fun getArticle(id: Int): Article
}