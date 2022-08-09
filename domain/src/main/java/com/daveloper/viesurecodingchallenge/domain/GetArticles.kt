package com.daveloper.viesurecodingchallenge.domain

import com.daveloper.viesurecodingchallenge.data.domain.ArticleRepository
import com.daveloper.viesurecodingchallenge.data.local.database.Article
import com.daveloper.viesurecodingchallenge.utils.Resource
import javax.inject.Inject


class GetArticles @Inject constructor(
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(): Resource<List<Article>> {
        return repository.getArticles()
    }
}