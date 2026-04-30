package com.example.techinventory.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.techinventory.data.model.Device
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InventoryViewModel : ViewModel(){
    //Variables de escritura
    private val _devices = MutableStateFlow<List<Device>>(emptyList())

    //Variables de lectura
    val devices = _devices.asStateFlow()

    //Crear un nuevo device
    fun registerDevice(name: String, type:String){
        val newDevice = Device(
            id = _devices.value.size + 1,
            name = name,
            type = type
        )

        _devices.value = _devices.value + newDevice
    }

    //Eliminar dispositivo
    fun deleteDevice(deviceId: Int){
        _devices.value = _devices.value.filter { it.id != deviceId }
    }

    fun getDeviceById(id: Int) : Device? {
        return _devices.value.find{it.id == id}
    }
}

