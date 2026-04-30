package com.example.techinventory.data.model

data class Device(
    val id: Int,
    val name: String,
    val type: String,
    val status: String = "Disponible"
)