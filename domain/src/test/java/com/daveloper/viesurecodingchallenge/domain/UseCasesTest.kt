package com.daveloper.viesurecodingchallenge.domain

import com.daveloper.viesurecodingchallenge.data.domain.ArticleRepository
import com.daveloper.viesurecodingchallenge.data.local.database.Article
import com.daveloper.viesurecodingchallenge.data.remote.ArticleDto
import com.daveloper.viesurecodingchallenge.data.remote.toArticle
import com.daveloper.viesurecodingchallenge.data.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.LocalDate


val testArticlesDao: List<ArticleDto> = listOf(
    ArticleDto(
        1,
        "Moonlight",
        "Mauris",
        "sdowdle0@shareasale.com",
        LocalDate.of(2018, 9, 22),
        "http://dummyimage.com/786x817.png/dddddd/000000"
    ),
    ArticleDto(
        2,
        "Moonlight",
        "City",
        "kpegg1@comsenz.com",
        LocalDate.of(2015, 4, 2),
        "http://dummyimage.com/709x366.png/cc0000/ffffff"
    ),
    ArticleDto(
        3,
        "Moonlight",
        "Grandmaster",
        "jarmit2@sfgate.com",
        LocalDate.of(2020, 8, 10),
        "http://dummyimage.com/302x740.png/5fa2dd/ffffff"
    ),
)

class UseCasesTest {
    private lateinit var getArticles: GetArticles
    private lateinit var getArticle: GetArticle
    private lateinit var fakeArticleRepository: FakeArticleRepository

    @Before
    fun setUp() {
        fakeArticleRepository = FakeArticleRepository()
        getArticle = GetArticle(fakeArticleRepository)
        getArticles = GetArticles(fakeArticleRepository)
    }

    @Test
    fun `Get Articles, check size`() {
        val resource = runBlocking {
            getArticles()
        }

        assertTrue(resource is Resource.Success)
        val articles = resource.data
        assertTrue(articles!!.size == 3)
    }

    @Test
    fun `Get Article, check correct item`() {
        val article = runBlocking {
            getArticle(3)
        }

        assertTrue(article.title == "Moonlight")
    }
}

class FakeArticleRepository: ArticleRepository {

    val articles = testArticlesDao.map { it.toArticle() }

    override suspend fun getArticles(): Resource<List<Article>> {
        return Resource.Success(articles)
    }

    override suspend fun getArticle(id: Int): Article {
        return articles.find { it.id == id } ?: throw Exception("not found")
    }
}
