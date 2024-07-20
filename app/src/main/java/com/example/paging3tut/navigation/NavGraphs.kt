package com.example.paging3tut.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.paging3tut.presentation.home.HomeScreen
import com.example.paging3tut.presentation.search.SearchScreen

@Composable
fun NavGraphs(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.routess
    ) {
        composable(route = Screen.Home.routess){
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Search.routess){
            SearchScreen(navController = navController)
        }
    }
}