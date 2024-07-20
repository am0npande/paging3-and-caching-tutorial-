package com.example.paging3tut.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.enableLiveLiterals
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.paging3tut.navigation.Screen
import com.example.paging3tut.presentation.common.ListContent

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val getImages = homeViewModel.getAllImagesContent.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            HomeTopBar(onSeachClicked = {
                navController.navigate(Screen.Search.routess)
            })
        },
        content = { ListContent(modifier = Modifier.padding(it),items = getImages) }
    )
}