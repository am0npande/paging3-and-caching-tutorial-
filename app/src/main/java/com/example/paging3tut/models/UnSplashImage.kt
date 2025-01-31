package com.example.paging3tut.models


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.paging3tut.util.Constants.UNSPLASH_IMAGE_TABLE
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = UNSPLASH_IMAGE_TABLE)
data class UnSplashImage(

    @PrimaryKey(autoGenerate = false)
    val id:String,

    @Embedded
    val urls: Urls,
    val likes:Int,

    @Embedded
    val user:User
)
