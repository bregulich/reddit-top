package com.bokugan.reddittop.datasource

import com.bokugan.reddittop.room.DatabaseProvider
import com.bokugan.reddittop.room.Post
import com.bokugan.reddittop.room.PostDao
import kotlinx.coroutines.flow.Flow

interface LocalPostDataSource {
    val posts: Flow<List<Post>>
    suspend fun updatePosts(posts: List<Post>)
}

private class TopLocalPostDataSource(private val dao: PostDao) : LocalPostDataSource {

    override val posts: Flow<List<Post>>
        get() = dao.getAll()

    override suspend fun updatePosts(posts: List<Post>) {
        dao.update(posts)
    }
}

private val TopLocalPostDataSourceInstance by lazy {
    TopLocalPostDataSource(
        DatabaseProvider.provide().postDao()
    )
}

object LocalPostDataSourceProvider {
    fun provide(): LocalPostDataSource = TopLocalPostDataSourceInstance
}