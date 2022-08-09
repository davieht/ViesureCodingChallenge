package com.daveloper.viesurecodingchallenge.data.local.repository

import com.daveloper.viesurecodingchallenge.data.local.database.ArticleDao
import com.daveloper.viesurecodingchallenge.data.remote.ArticleService
import com.daveloper.viesurecodingchallenge.data.local.database.ArticleDatabase
import com.daveloper.viesurecodingchallenge.data.remote.toArticleList
import com.daveloper.viesurecodingchallenge.data.util.Resource
import com.daveloper.viesurecodingchallenge.data.util.fetch
import com.daveloper.viesurecodingchallenge.domain.ArticleRepository
import com.daveloper.viesurecodingchallenge.domain.entities.Article
import kotlinx.coroutines.withContext
import javax.inject.Inject
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
