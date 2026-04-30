package com.example.navigation.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    title: String,
    description: String,
    onBack: () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de la tarea")},
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.ArrowBack, contentDescription =  "Atras")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = title)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = description)
            }
        }
    }
}
