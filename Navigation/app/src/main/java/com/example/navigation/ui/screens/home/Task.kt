package com.example.navigation.ui.screens.home

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false
)