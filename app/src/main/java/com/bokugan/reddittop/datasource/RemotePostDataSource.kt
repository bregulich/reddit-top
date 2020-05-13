package com.bokugan.reddittop.datasource

import com.bokugan.reddittop.redditapi.RedditAPI
import com.bokugan.reddittop.redditapi.RedditAPIProvider
import com.bokugan.reddittop.room.Post

interface RemotePostDataSource {
    suspend fun fetchPosts(): List<Post>
}

private class TopRemotePostDataSource(private val redditAPI: RedditAPI) : RemotePostDataSource {
    override suspend fun fetchPosts() = TODO()
}

private val TopRemotePostDataSourceInstance by lazy {
    TopRemotePostDataSource(RedditAPIProvider.provide())
}

object RemotePostDataSourceProvider {
    fun provide(): RemotePostDataSource = TopRemotePostDataSourceInstance
}