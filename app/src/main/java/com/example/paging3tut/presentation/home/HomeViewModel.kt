package com.example.paging3tut.presentation.home

import androidx.lifecycle.ViewModel
import com.example.paging3tut.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: Repository
) :ViewModel(){
    val getAllImagesContent = repository.getAllImages()
}