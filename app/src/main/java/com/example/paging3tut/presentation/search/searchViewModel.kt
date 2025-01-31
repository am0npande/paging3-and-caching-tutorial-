package com.example.paging3tut.presentation.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3tut.data.repository.Repository
import com.example.paging3tut.models.UnSplashImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class searchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){
    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchImagess = MutableStateFlow<PagingData<UnSplashImage>>(PagingData.empty())
    val searchImagess = _searchImagess

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchHeroes(query: String) {
        viewModelScope.launch {
            repository.searchImages(query = query).cachedIn(viewModelScope).collect {
                _searchImagess.value = it
            }
        }
    }

}