package com.bokugan.reddittop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bokugan.reddittop.redditapi.RedditAPIServiceProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            val yoofie = RedditAPIServiceProvider.provideRedditAPIService().top()
            yoofie.string()
        }
    }
}
