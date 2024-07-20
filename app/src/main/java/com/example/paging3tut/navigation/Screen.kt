package com.example.paging3tut.navigation

sealed class Screen(val routess : String) {
    object Home:Screen("home_Screen")
    object Search:Screen("search_Screen")

}