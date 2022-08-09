package com.daveloper.viesurecodingchallenge.di

import android.content.Context
import androidx.room.Room
import com.daveloper.viesurecodingchallenge.data.local.database.ArticleDao
import com.daveloper.viesurecodingchallenge.data.local.database.ArticleDatabase
import com.daveloper.viesurecodingchallenge.data.local.database.LocalDateConverter
import com.daveloper.viesurecodingchallenge.data.local.repository.ArticleRepositoryImpl
import com.daveloper.viesurecodingchallenge.data.remote.ArticleService
import com.daveloper.viesurecodingchallenge.domain.ArticleRepository
import com.daveloper.viesurecodingchallenge.domain.usecases.GetArticle
import com.daveloper.viesurecodingchallenge.domain.usecases.GetArticles
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import java.time.LocalDate
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideArticleRepository(
        articleService: ArticleService,
        dao: ArticleDao,
        coroutineContext: CoroutineContext
    ): ArticleRepository {
        return ArticleRepositoryImpl(
            articleService,
            dao,
            coroutineContext
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideArticleDao(db: ArticleDatabase): ArticleDao {
        return db.articleDao
    }

    @Provides
    @Singleton
    fun provideArticleDatabase(
        @ApplicationContext context: Context,
    ): ArticleDatabase {
        return Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            "article_db"
        )
            .build()
    }
}