package com.bokugan.reddittop.datasource

import androidx.paging.DataSource
import com.bokugan.reddittop.room.DatabaseProvider
import com.bokugan.reddittop.dataobject.Post
import com.bokugan.reddittop.room.PostDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface LocalPostDataSource {
    val posts: DataSource.Factory<Int, Post>
    suspend fun updatePosts(posts: List<Post>)
    suspend fun deletePosts()
}

private class TopLocalPostDataSource(private val dao: PostDao) : LocalPostDataSource {

    override val posts: DataSource.Factory<Int, Post>
        get() = dao.getPosts()

    override suspend fun updatePosts(posts: List<Post>) =
        withContext(Dispatchers.IO) {
            dao.update(posts)
        }

    override suspend fun deletePosts() {
        withContext(Dispatchers.IO) {
            dao.deletePosts()
        }
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