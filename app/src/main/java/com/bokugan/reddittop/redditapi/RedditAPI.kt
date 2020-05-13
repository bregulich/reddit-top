package com.bokugan.reddittop.redditapi

import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.http.GET

interface RedditAPI {
    @GET("top.json")
    suspend fun top(): ResponseBody
}

private val RedditAPIRetrofitInstance by lazy {
    Retrofit.Builder()
        .baseUrl("https://www.reddit.com/")
        .build()
        .create(RedditAPI::class.java)
}

object RedditAPIProvider {
    fun provide(): RedditAPI = RedditAPIRetrofitInstance
}