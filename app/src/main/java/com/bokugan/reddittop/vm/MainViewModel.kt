package com.bokugan.reddittop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.paging.toLiveData
import com.bokugan.reddittop.repository.PostRepository

class MainViewModel(private val repository: PostRepository) : ViewModel() {

    val posts = liveData {
        emit(repository.getPosts().toLiveData(10))
    }
}