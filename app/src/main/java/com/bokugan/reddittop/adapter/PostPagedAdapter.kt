package com.bokugan.reddittop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bokugan.reddittop.R
import com.bokugan.reddittop.dataobject.Post
import com.bumptech.glide.Glide

class PostPagedAdapter : PagedListAdapter<Post, PostViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.post_item, parent, false)
        )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) =
        holder.bindTo(getItem(position))
}

private object DiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post) =
        oldItem == newItem
}

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: AppCompatTextView = itemView.findViewById(R.id.title)
    private val thumbnail: AppCompatImageView = itemView.findViewById(R.id.thumbnail)

    fun bindTo(post: Post?) {
        val context = title.context

        title.text = post?.title
        Glide
            .with(context)
            .load(post?.thumbnailUrl)
            .into(thumbnail);
    }
}