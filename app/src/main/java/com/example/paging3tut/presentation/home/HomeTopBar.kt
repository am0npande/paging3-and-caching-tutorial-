package com.example.paging3tut.presentation.home

import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.paging3tut.navigation.Screen

@Composable
fun HomeTopBar(
    onSeachClicked:()->Unit
) {
    TopAppBar(title = {
        Text(
            text = "Home",
            color = MaterialTheme.colors.onSurface
        )
    },
        backgroundColor =MaterialTheme.colors.onPrimary,
        actions = {
            IconButton(onClick = onSeachClicked) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            }

        }
    )
}
@Composable
@Preview
fun prev(){
    HomeTopBar{}
}