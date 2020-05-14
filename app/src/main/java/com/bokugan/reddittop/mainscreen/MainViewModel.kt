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
            viewModelScope.launch { repository.refreshPosts() }
            return repository.posts.toLiveData(10)
        }
}