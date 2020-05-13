package com.bokugan.reddittop.datasource

import com.bokugan.reddittop.repository.Post
import kotlinx.coroutines.flow.Flow

interface LocalPostDataSource {
    val posts: Flow<List<Post>>
    suspend fun updatePosts(posts: List<Post>)
}

private class TopLocalPostDataSource : LocalPostDataSource {

    override val posts: Flow<List<Post>>
        get() = TODO("Not yet implemented")

    override suspend fun updatePosts(posts: List<Post>) {
        TODO("Not yet implemented")
    }
}

private val TopLocalPostDataSourceInstance by lazy { TopLocalPostDataSource() }

object LocalPostDataSourceProvider {
    fun provide() : LocalPostDataSource = TopLocalPostDataSourceInstance
}