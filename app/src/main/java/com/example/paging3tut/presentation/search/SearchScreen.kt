package com.example.paging3tut.presentation.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.paging3tut.presentation.common.ListContent

@OptIn(ExperimentalCoilApi::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: searchViewModel = hiltViewModel()
) {

    val searchQuery by searchViewModel.searchQuery
    val searchImages = searchViewModel.searchImagess.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchWidget(
                text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchHeroes(query = it)
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        content = { ListContent(modifier = Modifier.padding(it), items = searchImages)}
    )
}