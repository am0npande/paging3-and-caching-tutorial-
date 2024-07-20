package com.example.paging3tut.data.local.dao

import androidx.room.*
import com.example.paging3tut.models.UnsplashRemoteKeys

@Dao
interface UnsplashRemoteKeysDao {

    @Query("SELECT * FROM unsplash_remote_keys WHERE id = :id")
    suspend fun  getRemoteKeys(id:String): UnsplashRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys:List<UnsplashRemoteKeys>)

    @Query("DELETE FROM unsplash_remote_keys")
    suspend fun deleteRemoteKeys()


}