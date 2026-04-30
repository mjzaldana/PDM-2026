package com.example.techinventory.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.techinventory.ui.screens.add.AddDeviceScreen
import com.example.techinventory.ui.screens.detail.DeviceDetailScreen
import com.example.techinventory.ui.screens.list.InventoryListScreen
import com.example.techinventory.ui.viewmodel.InventoryViewModel

@Composable
fun InventoryApp(){
    val navController = rememberNavController()
    val inventoryViewModel: InventoryViewModel = viewModel()

    NavHost(navController = navController, startDestination = "inventory_view"){
        composable("inventory_view") {
            InventoryListScreen(
                viewModel = inventoryViewModel,
                onNavigateToAdd =  { navController.navigate("add_device") },
                onNavigateToDetail = { id ->
                    navController.navigate("device_detail/${id}")
                }
            )
        }

        composable("add_device") {
            AddDeviceScreen(
                viewModel = inventoryViewModel,
                onBack = {navController.popBackStack()}
            )
        }

        composable(
            "device_detail/{deviceId}",
            arguments = listOf(navArgument("deviceId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->

            val id = backStackEntry
                .arguments?.getInt("deviceId") ?: 0

            DeviceDetailScreen(
                deviceId = id,
                viewModel = inventoryViewModel,
                onBack = {navController.popBackStack()}
            )
        }
    }
}
