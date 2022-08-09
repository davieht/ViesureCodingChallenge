//package com.daveloper.viesurecodingchallenge.domain.usecases
//
//import com.daveloper.viesurecodingchallenge.data.local.repository.testArticlesDao
//import com.daveloper.viesurecodingchallenge.data.remote.toArticle
//import com.daveloper.viesurecodingchallenge.data.util.Resource
//import com.daveloper.viesurecodingchallenge.domain.ArticleRepository
//import com.daveloper.viesurecodingchallenge.domain.entities.Article
//import kotlinx.coroutines.runBlocking
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Test
//
//class UseCasesTest {
//    private lateinit var getArticles: GetArticles
//    private lateinit var getArticle: GetArticle
//    private lateinit var fakeArticleRepository: FakeArticleRepository
//
//    @Before
//    fun setUp() {
//        fakeArticleRepository = FakeArticleRepository()
//        getArticle = GetArticle(fakeArticleRepository)
//        getArticles = GetArticles(fakeArticleRepository)
//    }
//
//    @Test
//    fun `Get Articles, check size`() {
//        val resource = runBlocking {
//            getArticles()
//        }
//
//        assertTrue(resource is Resource.Success)
//        val articles = resource.data
//        assertTrue(articles!!.size == 3)
//    }
//
//    @Test
//    fun `Get Article, check correct item`() {
//        val article = runBlocking {
//            getArticle(3)
//        }
//
//        assertTrue(article.title == "Moonlight")
//    }
//}
//
//class FakeArticleRepository: ArticleRepository {
//
//    val articles = testArticlesDao.map { it.toArticle() }
//
//    override suspend fun getArticles(): Resource<List<Article>> {
//        return Resource.Success(articles)
//    }
//
//    override suspend fun getArticle(id: Int): Article {
//        return articles.find { it.id == id } ?: throw Exception("not found")
//    }
//}