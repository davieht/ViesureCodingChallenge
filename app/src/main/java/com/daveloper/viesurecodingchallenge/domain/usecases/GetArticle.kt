package com.daveloper.viesurecodingchallenge.domain.usecases


import com.daveloper.viesurecodingchallenge.data.domain.ArticleRepository
import com.daveloper.viesurecodingchallenge.data.local.database.Article

import javax.inject.Inject

class GetArticle @Inject constructor(
    private val repository: ArticleRepository
) {
    suspend operator fun invoke(id: Int): Article {
        return repository.getArticle(id)
    }
}