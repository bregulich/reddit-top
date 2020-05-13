package com.bokugan.reddittop.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getAll(): Flow<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(posts: List<Post>)
}