package com.example.paging3tut.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultModel(
    @SerialName("results")
    val images:List<UnSplashImage>
)
