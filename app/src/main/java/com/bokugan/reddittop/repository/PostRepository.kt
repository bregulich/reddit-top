package com.bokugan.reddittop.repository

import androidx.paging.DataSource
import com.bokugan.reddittop.dataobject.Post
import com.bokugan.reddittop.datasource.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface PostRepository {
    suspend fun getPosts(): DataSource.Factory<Int, Post>
}

private class PostRepositoryService(
    private val localDataSource: LocalPostDataSource,
    private val remoteDataSource: RemotePostDataSource
) : PostRepository {

    override suspend fun getPosts(): DataSource.Factory<Int, Post> {
        coroutineScope {
            launch { updatePosts(remoteDataSource.fetchPosts()) }
        }
        return localDataSource.posts
    }

    // TODO
    private suspend fun updatePosts(fetchResult: FetchResult) =
        withContext(Dispatchers.Default) {
            if (fetchResult is Success) {
                localDataSource.updatePosts(fetchResult.posts)
            } else {
                TODO()
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