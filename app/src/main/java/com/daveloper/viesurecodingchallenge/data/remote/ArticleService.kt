package com.daveloper.viesurecodingchallenge.data.remote

import com.daveloper.viesurecodingchallenge.data.remote.ArticleDto
import retrofit2.http.GET

interface ArticleService {
    @GET("/v3/5de1afae-d287-4210-a07e-eb73d55550bb")
    suspend fun getArticleItems(): List<ArticleDto>
}