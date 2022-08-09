package com.daveloper.viesurecodingchallenge.domain.usecases

import com.daveloper.viesurecodingchallenge.domain.ArticleRepository
import com.daveloper.viesurecodingchallenge.domain.entities.Article
import javax.inject.Inject

class GetArticle @Inject constructor(
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(id: Int): Article {
        return repository.getArticle(id)
    }
}