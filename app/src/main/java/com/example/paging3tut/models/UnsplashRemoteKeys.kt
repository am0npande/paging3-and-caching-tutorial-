package com.example.paging3tut.models

//main purpose of this remote keys table is to store prev and next pages key in local database so our remote mediator will know which page to request next

//so our remote mediator will handle pagination automatically
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.paging3tut.util.Constants.UNSPLASH_REMOTEKEYS_TABLE

@Entity(tableName = UNSPLASH_REMOTEKEYS_TABLE)
data class UnsplashRemoteKeys(

    @PrimaryKey(autoGenerate = false)
    val id:String,
    val prevPage:Int?,
    val nextPage:Int?
)
