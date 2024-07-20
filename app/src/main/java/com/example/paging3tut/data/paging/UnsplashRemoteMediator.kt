package com.example.paging3tut.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.paging3tut.data.local.UnsplashImageDatabase
import com.example.paging3tut.data.remote.UnsplashApi
import com.example.paging3tut.models.UnSplashImage
import com.example.paging3tut.models.UnsplashRemoteKeys
import com.example.paging3tut.util.Constants.ITEMS_PER_PAGE
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class UnsplashRemoteMediator (
    private val unsplashApi: UnsplashApi,
    private val unsplashImageDatabase: UnsplashImageDatabase
) : RemoteMediator<Int,UnSplashImage>()
{
    private val unsplashImageDao = unsplashImageDatabase.unsplashimagedao()
    private val unsplashRemoteKeyDao = unsplashImageDatabase.unsplashremotekeysdao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnSplashImage>,
    ): MediatorResult
    {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevspage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevspage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextpage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextpage
                }
            }
            val response =
                unsplashApi.getAllImages(page = currentPage, per_page = ITEMS_PER_PAGE) //return list of images
            val endOfPaginationReached = response.isEmpty() //check if list is empty


            val previouspage = if (currentPage == 1) null else currentPage - 1
            val nextpages = if (endOfPaginationReached) null else currentPage + 1

            unsplashImageDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    unsplashImageDao.deleteAllImages()
                    unsplashRemoteKeyDao.deleteRemoteKeys()
                }
                val keys = response.map {
                    UnsplashRemoteKeys(
                        id = it.id,
                        prevPage = previouspage,
                        nextPage = nextpages
                    )
                }
                unsplashRemoteKeyDao.addAllRemoteKeys(remoteKeys = keys)
                unsplashImageDao.addImages(images = response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

        }
        catch(e:Exception){ return MediatorResult.Error(e)}
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, UnSplashImage>
    ): UnsplashRemoteKeys?
    {
        return state.pages.lastOrNull{it.data.isNotEmpty()}?.data?.lastOrNull()?.
                let {
                    unsplashRemoteKeyDao.getRemoteKeys(id = it.id)
                }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UnSplashImage>
    ): UnsplashRemoteKeys?{

        return state.pages.firstOrNull{
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let {
            unsplashRemoteKeyDao.getRemoteKeys(id = it.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, UnSplashImage>
    ): UnsplashRemoteKeys?
    {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.id?.let {
                unsplashRemoteKeyDao.getRemoteKeys(id = it)
            }
        }
    }


}