package com.example.navigation.ui.Navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens{

    @Serializable
    object Home: Screens(){}

    @Serializable
    data class Details(
        val title: String,
        val desc: String
    ): Screens(){}
}