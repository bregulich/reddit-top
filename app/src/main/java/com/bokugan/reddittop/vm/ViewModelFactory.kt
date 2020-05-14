package com.bokugan.reddittop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bokugan.reddittop.mainscreen.MainViewModel
import com.bokugan.reddittop.repository.PostRepositoryProvider

class ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        if (modelClass == MainViewModel::class.java) {
            MainViewModel(PostRepositoryProvider.provide()) as T
        } else {
            throw Exception("Unknown vm")
        }
}