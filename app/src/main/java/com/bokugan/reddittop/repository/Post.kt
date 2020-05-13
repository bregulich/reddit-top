package com.bokugan.reddittop.repository

import androidx.room.PrimaryKey

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