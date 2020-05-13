package com.bokugan.reddittop.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

interface RedditAPI {
    @GET("top.json")
    fun top(): Call<ResponseBody>
}

private val RedditAPIService by lazy {
    Retrofit.Builder()
        .baseUrl("https://www.reddit.com/")
        .build()
        .create(RedditAPI::class.java)
}

object RedditAPIServiceProvider {
    fun provideRedditAPIService(): RedditAPI = RedditAPIService
}