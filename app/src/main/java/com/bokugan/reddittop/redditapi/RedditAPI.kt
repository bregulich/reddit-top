package com.bokugan.reddittop.redditapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditAPI {
    @GET("top.json")
    suspend fun top(
        @Query("after") afterId: String? = null,
        @Query("before") beforeId: String? = null,
        @Query("limit") limit: Int = LIMIT
    ): TopJsonResponse

    companion object {
        private const val LIMIT = 10
    }
}

private val RedditAPIRetrofitInstance by lazy {
    Retrofit.Builder()
        .baseUrl("https://www.reddit.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RedditAPI::class.java)
}

object RedditAPIProvider {
    fun provide(): RedditAPI = RedditAPIRetrofitInstance
}