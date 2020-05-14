package com.bokugan.reddittop.mainscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bokugan.reddittop.R
import com.bokugan.reddittop.adapter.PostPagedAdapter
import com.bokugan.reddittop.vm.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val vm by viewModels<MainViewModel> { ViewModelFactory() }
    private val postAdapter = PostPagedAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.adapter = postAdapter

        vm.posts.observe(this, Observer { postAdapter.submitList(it) })
    }
}
