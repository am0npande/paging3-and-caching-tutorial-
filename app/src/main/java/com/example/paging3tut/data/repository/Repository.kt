package com.example.paging3tut.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paging3tut.data.local.UnsplashImageDatabase
import com.example.paging3tut.data.paging.SearchPagingSources
import com.example.paging3tut.data.paging.UnsplashRemoteMediator
import com.example.paging3tut.data.remote.UnsplashApi
import com.example.paging3tut.models.UnSplashImage
import com.example.paging3tut.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class Repository @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val unsplashImageDatabase: UnsplashImageDatabase
){
    @OptIn(ExperimentalPagingApi::class)
    fun getAllImages(): Flow<PagingData<UnSplashImage>> {
        val pagingFactory = {unsplashImageDatabase.unsplashimagedao().getAllImages()}
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(unsplashApi,unsplashImageDatabase),
            pagingSourceFactory = pagingFactory
        ).flow
    }
    fun searchImages(query:String):Flow<PagingData<UnSplashImage>>{
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSources(unsplashApi,query = query)
            }
        ).flow
    }

}