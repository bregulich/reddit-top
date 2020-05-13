package com.bokugan.reddittop.repository

import com.bokugan.reddittop.datasource.LocalPostDataSource
import com.bokugan.reddittop.datasource.LocalPostDataSourceProvider
import com.bokugan.reddittop.datasource.RemotePostDataSource
import com.bokugan.reddittop.datasource.RemotePostDataSourceProvider
import com.bokugan.reddittop.room.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

interface PostRepository {
    val posts: Flow<List<Post>>
    suspend fun updatePosts(posts: List<Post>)
}

private class PostRepositoryService(
    private val coroutineScope: CoroutineScope,
    private val localDataSource: LocalPostDataSource,
    private val remoteDataSource: RemotePostDataSource
) : PostRepository {

    override val posts: Flow<List<Post>>
        get() {
            coroutineScope.launch { updatePosts(remoteDataSource.fetchPosts()) }
            return localDataSource.posts
        }

    override suspend fun updatePosts(posts: List<Post>) = localDataSource.updatePosts(posts)
}

private val PostRepositoryInstance by lazy {
    PostRepositoryService(
        GlobalScope,
        LocalPostDataSourceProvider.provide(),
        RemotePostDataSourceProvider.provide()
    )
}

object PostRepositoryProvider {
    fun provide(): PostRepository = PostRepositoryInstance
}