package com.bokugan.reddittop.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bokugan.reddittop.dataobject.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getPosts(): DataSource.Factory<Int, Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(posts: List<Post>)
}