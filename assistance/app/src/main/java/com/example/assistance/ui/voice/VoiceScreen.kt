package com.example.assistance.ui.voice

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.assistance.viewModel.VoiceViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun VoiceScreen(viewModel: VoiceViewModel){

    val textoReconocido by viewModel.textReconocido.collectAsState()
    var input by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Asistente")

        TextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Ingresa un comando")},
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.procesarTexto(input)
                input = ""
            }
        ) {
            Text("Ejecutar")
        }
    }
}
