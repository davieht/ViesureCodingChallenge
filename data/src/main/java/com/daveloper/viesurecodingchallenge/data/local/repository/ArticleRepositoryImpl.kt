package com.daveloper.viesurecodingchallenge.data.local.repository

import com.daveloper.viesurecodingchallenge.data.domain.ArticleRepository
import com.daveloper.viesurecodingchallenge.data.local.database.Article
import com.daveloper.viesurecodingchallenge.data.local.database.ArticleDao
import com.daveloper.viesurecodingchallenge.data.remote.ArticleService
import com.daveloper.viesurecodingchallenge.data.remote.toArticleList
import com.daveloper.viesurecodingchallenge.utils.Resource
import com.daveloper.viesurecodingchallenge.utils.fetch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ArticleRepositoryImpl(
    private val articleService: ArticleService,
    private val articleDao: ArticleDao,
    private val coroutineContext: CoroutineContext
) : ArticleRepository {
    override suspend fun getArticles(): Resource<List<Article>> {
        return withContext(coroutineContext) {
            fetch(
                block = {
                    articleService.getArticleItems().toArticleList()
                },
                fetchFromDatabase = {
                    articleDao.getArticleItems().sortedByDescending { it.releaseDate }
                },
                insertToDatabase = {
                    articleDao.insertAll(it)
                })
        }
    }

    override suspend fun getArticle(id: Int): Article {
        return withContext(coroutineContext) {
            articleDao.getArticleItem(id)
        }
    }
}
