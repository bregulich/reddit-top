package com.bokugan.reddittop.mainscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.bokugan.reddittop.dataobject.Post
import com.bokugan.reddittop.repository.PostRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PostRepository) : ViewModel() {

    val posts: LiveData<PagedList<Post>>
        get() {
            viewModelScope.launch { repository.fetchPosts() }
            return repository.posts.toLiveData(
                pageSize = 10,
                boundaryCallback = BoundaryCallback()
            )
        }

    private inner class BoundaryCallback() : PagedList.BoundaryCallback<Post>() {

        override fun onZeroItemsLoaded() {
            super.onZeroItemsLoaded()
        }

        override fun onItemAtEndLoaded(itemAtEnd: Post) {
            viewModelScope.launch {
                repository.fetchAfter()
            }
        }

        override fun onItemAtFrontLoaded(itemAtFront: Post) {
            viewModelScope.launch {
                repository.fetchBefore()
            }
        }
    }
}