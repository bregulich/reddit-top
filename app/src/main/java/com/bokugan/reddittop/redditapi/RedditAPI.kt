package com.bokugan.reddittop.redditapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RedditAPI {
    @GET("top.json")
    suspend fun top(): TopJsonResponse
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