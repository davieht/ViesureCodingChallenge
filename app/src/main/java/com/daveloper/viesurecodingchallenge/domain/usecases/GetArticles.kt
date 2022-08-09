package com.daveloper.viesurecodingchallenge.domain.usecases

import com.daveloper.viesurecodingchallenge.data.util.Resource
import com.daveloper.viesurecodingchallenge.domain.ArticleRepository
import com.daveloper.viesurecodingchallenge.domain.entities.Article
import javax.inject.Inject

class GetArticles @Inject constructor(
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(): Resource<List<Article>> {
        return repository.getArticles()
    }
}