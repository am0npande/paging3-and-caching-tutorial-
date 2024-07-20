package com.example.paging3tut.data.remote

import com.example.paging3tut.models.SearchResultModel
import com.example.paging3tut.models.UnSplashImage
import com.example.paging3tut.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//this interface contain 2 different  "GET" requests

interface UnsplashApi
{
    @Headers("Authorization:Client-ID ${API_KEY}")
    @GET("/photos")
    suspend fun getAllImages(

        @Query(value = "page") page:Int,

        @Query(value = "per_page") per_page:Int

    ):  List<UnSplashImage>

    @Headers("Authorization:Client-ID ${API_KEY}")
    @GET("/search/photos")
    suspend fun getAllSearch(

        @Query("query") query: String,

        @Query("per_page") per_page:Int

    ):  SearchResultModel



}