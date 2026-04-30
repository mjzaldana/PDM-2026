package com.example.techinventory.ui.screens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import com.example.techinventory.ui.viewmodel.InventoryViewModel

@Composable
fun AddDeviceScreen(
    viewModel: InventoryViewModel,
    onBack: () -> Unit
){
    var name by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize()
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre de dispositivo")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = type,
            onValueChange = { name = it },
            label = { Text("Tipo de dispositivo")},
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if(name.isNotBlank() && type.isNotBlank()){
                    viewModel.registerDevice(name, type)
                    //Opcional si desean volver a la pantalla anterior
                    onBack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar dispositivo")
        }
    }
}


