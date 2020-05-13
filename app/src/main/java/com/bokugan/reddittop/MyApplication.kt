package com.bokugan.reddittop

import android.app.Application
import android.content.Context

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ApplicationContext = this
    }

    companion object {
        lateinit var ApplicationContext: Context
            private set
    }
}