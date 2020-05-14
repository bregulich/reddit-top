package com.bokugan.reddittop.repository

import androidx.paging.DataSource
import com.bokugan.reddittop.dataobject.Post
import com.bokugan.reddittop.datasource.*

interface PostRepository {
    val posts: DataSource.Factory<Int, Post>
    suspend fun refreshPosts()
    suspend fun fetchAfter()
    suspend fun fetchBefore()
}

private class PostRepositoryService(
    private val localDataSource: LocalPostDataSource,
    private val remoteDataSource: RemotePostDataSource
) : PostRepository {

    // TODO. Not ideal for app restarts.
    private var afterId: String? = null
    private var beforeId: String? = null

    override val posts: DataSource.Factory<Int, Post>
        get() = localDataSource.posts

    override suspend fun refreshPosts() {
        afterId = null
        beforeId = null

        val fetchResult = remoteDataSource.fetchPosts()
        if (fetchResult is Success) {
            localDataSource.deletePosts()
            this.fetchPosts(null, null)
        } else {
//            TODO("Error handling")
        }
    }

    override suspend fun fetchAfter() {
        if (!afterId.isNullOrBlank()) {
            fetchPosts(afterId = afterId)
        }
    }

    override suspend fun fetchBefore() {
        if (!beforeId.isNullOrBlank()) {
            fetchPosts(beforeId = beforeId)
        }
    }

    private suspend fun fetchPosts(afterId: String? = null, beforeId: String? = null) {
        val fetchResult = remoteDataSource.fetchPosts(afterId, beforeId)
        if (fetchResult is Success) {
            this.afterId = fetchResult.after
            this.beforeId = fetchResult.before
            localDataSource.updatePosts(fetchResult.posts)
        } else {
//            TODO("Error handling")
        }
    }
}

private val PostRepositoryInstance by lazy {
    PostRepositoryService(
        LocalPostDataSourceProvider.provide(),
        RemotePostDataSourceProvider.provide()
    )
}

object PostRepositoryProvider {
    fun provide(): PostRepository = PostRepositoryInstance
}