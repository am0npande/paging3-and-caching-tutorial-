package com.example.paging3tut.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.paging3tut.models.UnSplashImage

@Dao
interface UnsplashImageDao {

    @Query("SELECT * FROM unsplash_image_table")
    fun getAllImages():PagingSource<Int,UnSplashImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImages(images: List<UnSplashImage>)


    @Query("DELETE FROM unsplash_image_table")
    suspend fun deleteAllImages()
}