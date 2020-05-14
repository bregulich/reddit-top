package com.bokugan.reddittop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bokugan.reddittop.R
import com.bokugan.reddittop.dataobject.Post

class PostPagedAdapter : PagedListAdapter<Post, PostViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.post_item, parent, false)
        )

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) =
        holder.bindTo(getItem(position))

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post) =
                oldItem == newItem
        }
    }
}

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val text: AppCompatTextView = itemView.findViewById(R.id.text)

    fun bindTo(post: Post?) {
        text.text = post?.title
    }
}