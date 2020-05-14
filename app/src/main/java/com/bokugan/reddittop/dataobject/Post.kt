package com.bokugan.reddittop.dataobject

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Post(

    @SerializedName("name")
    @PrimaryKey
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("author")
    val author: String,

    @SerializedName("subreddit_name_prefixed")
    val subreddit: String,

    @SerializedName("created_utc")
    val date: Long,

    @SerializedName("thumbnail")
    val thumbnailUrl: String?,

    @SerializedName("score")
    val score: Int,

    @SerializedName("num_comments")
    val comments: Int,

    @SerializedName("permalink")
    val permalink: String
)

fun Post.openRedditPost(context: Context) {
    CustomTabsIntent.Builder().build().launchUrl(
        context,
        // TODO. VM logic.
        Uri.parse(REDDIT_HOST + permalink)
    )
}

private const val REDDIT_HOST = "https://www.reddit.com"