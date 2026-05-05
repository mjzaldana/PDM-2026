package com.example.notes.data

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class VisualNode(
    val id: String,
    val title: String,
    val offset: Animatable<Offset, AnimationVector2D> = Animatable(
        Offset((200..800).random().toFloat(), (400..1000).random().toFloat()),
        Offset.VectorConverter
    ),
    val color: Color = Color(0xFF00E5FF)
)