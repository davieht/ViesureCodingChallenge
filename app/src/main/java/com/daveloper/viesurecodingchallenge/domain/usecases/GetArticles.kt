package com.daveloper.viesurecodingchallenge.domain.usecases

import com.daveloper.viesurecodingchallenge.data.domain.ArticleRepository
import com.daveloper.viesurecodingchallenge.data.local.database.Article
import com.daveloper.viesurecodingchallenge.data.util.Resource
import javax.inject.Inject

class GetArticles @Inject constructor(
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(): Resource<List<Article>> {
        return repository.getArticles()
    }
}