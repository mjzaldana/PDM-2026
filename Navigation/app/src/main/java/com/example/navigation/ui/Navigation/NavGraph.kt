package com.example.navigation.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.navigation.ui.screens.detail.DetailScreen
import com.example.navigation.ui.screens.home.HomeScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home
    ) {

        composable<Screens.Home> {
            HomeScreen(
                viewModel = viewModel(),
                onNavigateToDetails = { title, desc ->
                    navController.navigate(Screens.Details(title, desc))
                }
            )
        }

        composable<Screens.Details> { backStackEntry ->
            val args: Screens.Details = backStackEntry.toRoute()

            DetailScreen(
                title = args.title,
                description = args.desc,
                onBack = { navController.popBackStack() }
            )
        }
    }
}