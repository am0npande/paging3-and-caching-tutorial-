package com.example.paging3tut

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.paging3tut.navigation.NavGraphs
import com.example.paging3tut.ui.theme.Paging3TUTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Paging3TUTTheme {
                val navController = rememberNavController()
                NavGraphs(navController = navController)
            }
        }
    }
}

