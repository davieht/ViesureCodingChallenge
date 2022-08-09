//package com.daveloper.viesurecodingchallenge.data.local.repository
//
//import ArticleDao
//import ArticleDto
//import ArticleService
//import Resource
//import com.daveloper.viesurecodingchallenge.di.AppModule
//import com.daveloper.viesurecodingchallenge.di.DatabaseModule
//import com.daveloper.viesurecodingchallenge.domain.entities.Article
//import dagger.hilt.android.testing.HiltAndroidTest
//import dagger.hilt.android.testing.UninstallModules
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.runBlocking
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Test
//import java.time.LocalDate
//
//val testArticlesDao: List<ArticleDto> = listOf(
//    ArticleDto(
//        1,
//        "Moonlight",
//        "Mauris",
//        "sdowdle0@shareasale.com",
//        LocalDate.of(2018, 9, 22),
//        "http://dummyimage.com/786x817.png/dddddd/000000"
//    ),
//    ArticleDto(
//        2,
//        "Moonlight",
//        "City",
//        "kpegg1@comsenz.com",
//        LocalDate.of(2015, 4, 2),
//        "http://dummyimage.com/709x366.png/cc0000/ffffff"
//    ),
//    ArticleDto(
//        3,
//        "Moonlight",
//        "Grandmaster",
//        "jarmit2@sfgate.com",
//        LocalDate.of(2020, 8, 10),
//        "http://dummyimage.com/302x740.png/5fa2dd/ffffff"
//    ),
//)
//
//@HiltAndroidTest
//@UninstallModules(AppModule::class, DatabaseModule::class)
//class ArticleRepositoryTest {
//    private lateinit var fakeArticleDao: ArticleDao
//    private lateinit var fakeArticleService: ArticleService
//    private lateinit var articleRepository: ArticleRepositoryImpl
//
//    @Before
//    fun setUp() {
//        fakeArticleDao = FakeArticleDao()
//        fakeArticleService = FakeArticleService()
//        articleRepository = ArticleRepositoryImpl(
//            fakeArticleService,
//            fakeArticleDao,
//            Dispatchers.IO
//        )
//    }
//
//    @Test
//    fun `Get articles from repository, check order`() {
//        val resource = runBlocking {
//            articleRepository.getArticles()
//        }
//
//        assertTrue(resource is Resource.Success)
//        val articles = resource.data
//        assertTrue(articles!!.size == 3)
//        for (i in 0..articles.size - 2) {
//            assertTrue(articles[i].releaseDate.isAfter(articles[i+1].releaseDate))
//        }
//    }
//
//    @Test
//    fun `Get article from repository, check correct item`() {
//        val article = runBlocking {
//            articleRepository.getArticles()
//            articleRepository.getArticle(3)
//        }
//
//        assertTrue(article.title == "Moonlight")
//    }
//}
//
//class FakeArticleService: ArticleService {
//    override suspend fun getArticleItems(): List<ArticleDto> {
//        return testArticlesDao
//    }
//}
//
//class FakeArticleDao: ArticleDao {
//    val listInDb: MutableList<Article> = mutableListOf()
//    override suspend fun getArticleItems(): List<Article> {
//        return listInDb
//    }
//
//    override suspend fun getArticleItem(id: Int): Article {
//        return listInDb.find { it.id == id } ?: throw Exception("not found")
//    }
//
//    override suspend fun insertAll(articles: List<Article>) {
//        listInDb.clear()
//        listInDb.addAll(articles)
//    }
//
//    override suspend fun deleteArticle(article: Article) {
//        // not used
//    }
//}