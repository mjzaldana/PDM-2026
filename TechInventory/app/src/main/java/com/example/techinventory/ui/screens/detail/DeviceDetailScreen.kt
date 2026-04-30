package com.example.techinventory.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.techinventory.ui.viewmodel.InventoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceDetailScreen(
    deviceId: Int,
    viewModel: InventoryViewModel,
    onBack: () -> Unit
){

    val device = viewModel.getDeviceById(deviceId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Detalle del dispositivo")},
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
       if (device != null){
          Column(
              modifier = Modifier.padding(padding)
          ) {
              Card(
                  modifier = Modifier.fillMaxWidth()
              ) {
                  Column(
                      modifier = Modifier.padding(16.dp)
                  ) {
                      Text("Nombre: ")
                      Text(device.name)

                      Text("Tipo: ")
                      Text(device.type)
                  }
              }
          }
       }else{
           Box(
               modifier = Modifier.fillMaxSize(),
               contentAlignment = Alignment.Center
           ) {
               Text("Dispositivo no encontrado")
           }
       }
    }
}