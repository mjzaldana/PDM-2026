package com.example.navigation.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToDetails: (String, String) -> Unit
){
    //Permite tener contexto del estado actual
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Mis tareas")
            })
        }
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(padding)
        ){
            when(val s = state){
                is HomeUiState.Loading -> CircularProgressIndicator(
                    Modifier.align(Alignment.Center)
                )
                is HomeUiState.Success -> {
                    LazyColumn{
                        items(s.tasks){ task ->
                            ListItem(
                                headlineContent = {
                                    Text(task.title)
                                },
                                supportingContent = {
                                    Text(task.description)
                                },
                                modifier = Modifier.clickable{
                                    // TODO: navigation
                                }
                            )
                        }
                    }
                }
                is HomeUiState.Error -> Text("Error: ${s.message}")
            }
        }
    }
}