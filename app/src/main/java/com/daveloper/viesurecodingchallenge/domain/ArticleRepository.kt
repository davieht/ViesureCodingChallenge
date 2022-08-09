package com.daveloper.viesurecodingchallenge.domain

import com.daveloper.viesurecodingchallenge.data.util.Resource
import com.daveloper.viesurecodingchallenge.domain.entities.Article

interface ArticleRepository {
    suspend fun getArticles(): Resource<List<Article>>
    suspend fun getArticle(id: Int): Article
}