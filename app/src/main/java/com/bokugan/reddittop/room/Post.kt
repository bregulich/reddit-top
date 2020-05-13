package com.bokugan.reddittop.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey val id: String,
    val title: String,
    val author: String,
    val subreddit: String,
    val date: String,
    val thumbnailUrl: String?,
    val rating: Int,
    val comments: Int
)