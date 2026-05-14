package com.example.streamingapp.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streamingapp.api.RetrofitInstance
import com.example.streamingapp.data.Show
import kotlinx.coroutines.launch

class ShowViewModel: ViewModel(){

    //lista de series a mostrar
    var shows by mutableStateOf<List<Show>>(emptyList())
        private set

    //Estado para saber si un show esta seleccionado para ver detalle
    var selectedShow by mutableStateOf<Show?>(null)
        private set

    //Para saber si debo esperar un resultado
    var isLoading by mutableStateOf(false)

    init {
        loadShows("Action")
    }

    fun loadShows(query: String){
        viewModelScope.launch {
            isLoading = true
            try {
                val response = RetrofitInstance.api.searchShows(query)
                shows = response.map { it.show }
            }catch (e: Exception){
                //TODO
            } finally {
                isLoading = false
            }
        }
    }

    fun selectShow(show: Show?){
        selectedShow = show
    }

}