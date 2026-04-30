package com.example.navigation.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class HomeUiState{

    //Estado de carga
    object Loading: HomeUiState()

    //Respuesta exitosa
    data class Success(val tasks: List<Task>) : HomeUiState()

    //Respuesta fallida
    data class Error(val message: String): HomeUiState()
}

class HomeViewModel : ViewModel(){

    //Atributos de escritura
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)

    //Atributos de lectura
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadTasks()
    }

    private fun loadTasks(){
        viewModelScope.launch {
            delay(1500)
            _uiState.value = HomeUiState.Success(
                listOf(
                    Task(1,"Tarea 1", "Esta es tarea 1"),
                    Task(2,"Tarea 2", "Esta es tarea 2"),
                    Task(3,"Tarea 3", "Esta es tarea 3")
                )
            )
        }
    }
}