package com.example.paging3tut.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

import com.example.paging3tut.data.remote.UnsplashApi
import com.example.paging3tut.models.UnSplashImage
import com.example.paging3tut.util.Constants.ITEMS_PER_PAGE
import retrofit2.http.Query

class SearchPagingSources(
    private val unsplashApi: UnsplashApi,
    private val query: String
):PagingSource<Int,UnSplashImage>() {
    override fun getRefreshKey(state: PagingState<Int, UnSplashImage>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnSplashImage> {
        val currentpage = params.key?:1
        return try {
            val response = unsplashApi.getAllSearch(query = query, per_page = ITEMS_PER_PAGE)
            val endofpaginationreached = response.images.isEmpty()
            if(response.images.isNotEmpty()){
                LoadResult.Page(
                    data = response.images,
                    prevKey = if(currentpage == 1) null else currentpage - 1,
                    nextKey = if(endofpaginationreached) null else currentpage + 1
                )
            }
            else{
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        }
        catch (e:Exception){LoadResult.Error(e)}
    }




}