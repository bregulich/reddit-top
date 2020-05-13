package com.bokugan.reddittop.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bokugan.reddittop.MyApplication

private const val DB_NAME = "reddit-posts"

@Database(entities = [Post::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}

private val DatabaseInstance: PostDatabase by lazy {
    Room.databaseBuilder(
        MyApplication.ApplicationContext,
        PostDatabase::class.java,
        DB_NAME
    ).build()
}

object DatabaseProvider {
    fun provide(): PostDatabase = DatabaseInstance
}