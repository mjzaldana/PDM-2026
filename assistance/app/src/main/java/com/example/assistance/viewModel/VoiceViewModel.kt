package com.example.assistance.viewModel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import com.example.assistance.device.DeviceController
import com.example.assistance.voice.VoiceManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VoiceViewModel(private val context: Context) : ViewModel() {

    // Define cuando y como se realizan las acciones (Logica)
    private val _textoReconocido = MutableStateFlow("Di un comando...")
    val textReconocido: StateFlow<String> = _textoReconocido

    private val deviceController = DeviceController(context)

    @RequiresApi(Build.VERSION_CODES.O)
    fun procesarTexto(texto: String){
        _textoReconocido.value = texto
        procesarComando(texto.lowercase())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun procesarComando(texto: String){
        when {
            texto.contains("linterna") && texto.contains("enciende")->
                deviceController.linterna(true)

            texto.contains("linterna") && texto.contains("apaga")->
                deviceController.linterna(false)

            texto.contains("vibra")->
                deviceController.vibrar()

            texto.contains("sube") && texto.contains("brillo")->
                deviceController.brillo(255)

            texto.contains("baja") && texto.contains("brillo")->
                deviceController.brillo(50)
        }
    }
}