package com.example.paging3tut.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Urls(

    @SerialName("regular")  //errror
    val regularImage:String
)
