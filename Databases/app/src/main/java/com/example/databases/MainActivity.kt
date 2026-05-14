package com.example.databases

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.databases.ui.menu.MenuScreen
import com.example.databases.ui.menu.MenuViewModel
import com.example.databases.ui.menu.MenuViewModelFactory
import com.example.databases.ui.theme.DatabasesTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MenuViewModel by viewModels {
        MenuViewModelFactory((application as RestaurantApp).database.restaurantDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DatabasesTheme {
                MenuScreen(viewModel = viewModel)
            }
        }
    }
}
