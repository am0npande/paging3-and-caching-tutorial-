package com.example.paging3tut.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.paging3tut.data.local.dao.UnsplashImageDao
import com.example.paging3tut.data.local.dao.UnsplashRemoteKeysDao
import com.example.paging3tut.models.UnSplashImage
import com.example.paging3tut.models.UnsplashRemoteKeys

@Database(entities = [UnSplashImage::class,UnsplashRemoteKeys::class],version = 1)
abstract class UnsplashImageDatabase:RoomDatabase()
{
    abstract fun unsplashimagedao():UnsplashImageDao
    abstract fun unsplashremotekeysdao():UnsplashRemoteKeysDao
}