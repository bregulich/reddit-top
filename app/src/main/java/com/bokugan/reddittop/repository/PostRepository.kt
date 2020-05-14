package com.bokugan.reddittop.repository

import androidx.paging.DataSource
import com.bokugan.reddittop.dataobject.Post
import com.bokugan.reddittop.datasource.*

interface PostRepository {
    val posts: DataSource.Factory<Int, Post>
    suspend fun refreshPosts()
}

private class PostRepositoryService(
    private val localDataSource: LocalPostDataSource,
    private val remoteDataSource: RemotePostDataSource
) : PostRepository {

    override val posts: DataSource.Factory<Int, Post>
        get() = localDataSource.posts

    override suspend fun refreshPosts() =
        remoteDataSource.fetchPosts().run {
            if (this is Success) localDataSource.updatePosts(posts)
            else TODO()
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