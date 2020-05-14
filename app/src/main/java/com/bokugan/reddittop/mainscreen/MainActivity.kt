package com.bokugan.reddittop.mainscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

import androidx.lifecycle.Observer
import com.bokugan.reddittop.R
import com.bokugan.reddittop.adapter.OnItemClickListener
import com.bokugan.reddittop.adapter.PostPagedAdapter
import com.bokugan.reddittop.dataobject.Post
import com.bokugan.reddittop.dataobject.openRedditPost
import com.bokugan.reddittop.vm.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private val vm by viewModels<MainViewModel> { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PostPagedAdapter(this)
        recycler_view.adapter = adapter

        refresh_layout.setOnRefreshListener {
            vm.refreshPosts()
            // TODO. VM LiveData.
            refresh_layout.isRefreshing = false
        }

        vm.posts.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    override fun onItemClick(post: Post?) {
        post?.openRedditPost(this)
    }
}
