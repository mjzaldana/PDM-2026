package com.example.databases.ui.menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.databases.data.local.entities.CategoryWithDishes
import com.example.databases.data.local.entities.DishEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(viewModel: MenuViewModel){
    val categoriesWithDishes by viewModel.categoriesWithDishes.collectAsState()
    var showAddCategoryDialog by remember { mutableStateOf(false) }
    var showAddDishDialog by remember { mutableStateOf(false) }
    var selectedCategoryId by remember { mutableStateOf<Long?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Menu del restaurante")})
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddCategoryDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar categoría")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categoriesWithDishes) { item ->
                CategorySection(
                    categoryWithDishes = item,
                    onAddDish = {
                        selectedCategoryId = item.category.id
                        showAddDishDialog = true
                    },
                    onDeleteDish = {
                        viewModel.deleteDish(it)
                    }
                )
            }
        }

        if(showAddCategoryDialog) {
            AddCategoryDialog(
             onDismiss = {showAddCategoryDialog = false},
                onConfirm = { name ->
                    viewModel.addCategory(name)
                    showAddCategoryDialog = false
                }
            )
        }

        if(showAddDishDialog && selectedCategoryId != null) {
            AddDishDialog(
                onDismiss = {showAddDishDialog = false},
                onConfirm = { name, price ->
                    viewModel.addDish(name, price, selectedCategoryId!!)
                    showAddDishDialog = false
                }
            )
        }
    }
}

@Composable
fun CategorySection(
    categoryWithDishes: CategoryWithDishes,
    onAddDish: () -> Unit,
    onDeleteDish: (DishEntity) -> Unit,
){
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = categoryWithDishes.category.name,
                    style = MaterialTheme.typography.headlineSmall
                )
                IconButton(onClick = onAddDish) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar platillo")
                }
            }
            categoryWithDishes.dishes.forEach { dish ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column{
                        Text(
                            text = dish.name,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "$ ${dish.price}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    IconButton(onClick = {onDeleteDish(dish)}) {
                        Icon(Icons.Default.Delete, contentDescription = "Eliminar platillo")
                    }
                }
            }
        }
    }
}

@Composable
fun AddCategoryDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
){
    var name by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nueva categoria")},
        text = {
            TextField(value = name, onValueChange = {name = it}, label = { Text("Nombre")})
        },
        confirmButton = {
            Button(onClick = { onConfirm(name) }) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss }) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun AddDishDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, Double) -> Unit
){
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nuevo Platillo")},
        text = {
            Column {
                TextField(value = name, onValueChange = {name = it}, label = { Text("Nombre")})
                TextField(value = price, onValueChange = {price = it}, label = { Text("Precio")})
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(name, price.toDoubleOrNull()?:0.0) }) {
                Text("Agregar")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss }) {
                Text("Cancelar")
            }
        }
    )
}

