package com.bokugan.reddittop.datasource

import androidx.paging.DataSource
import com.bokugan.reddittop.room.DatabaseProvider
import com.bokugan.reddittop.dataobject.Post
import com.bokugan.reddittop.room.PostDao

interface LocalPostDataSource {
    val posts: DataSource.Factory<Int, Post>
    suspend fun updatePosts(posts: List<Post>)
}

private class TopLocalPostDataSource(private val dao: PostDao) : LocalPostDataSource {

    override val posts: DataSource.Factory<Int, Post>
        get() = dao.postsByDate()

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