package com.bokugan.reddittop.repository

import com.bokugan.reddittop.datasource.LocalPostDataSource
import com.bokugan.reddittop.datasource.LocalPostDataSourceProvider
import com.bokugan.reddittop.datasource.RemotePostDataSource
import com.bokugan.reddittop.datasource.RemotePostDataSourceProvider
import com.bokugan.reddittop.dataobject.Post
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

interface PostRepository {
    suspend fun fetchPosts(): Flow<List<Post>>
    suspend fun updatePosts(posts: List<Post>)
}

private class PostRepositoryService(
    private val localDataSource: LocalPostDataSource,
    private val remoteDataSource: RemotePostDataSource
) : PostRepository {

    override suspend fun fetchPosts(): Flow<List<Post>> {
        coroutineScope {
            //launch { updatePosts(remoteDataSource.fetchPosts()) }
        }
        return localDataSource.posts
    }

    override suspend fun updatePosts(posts: List<Post>) =
        localDataSource.updatePosts(posts)
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