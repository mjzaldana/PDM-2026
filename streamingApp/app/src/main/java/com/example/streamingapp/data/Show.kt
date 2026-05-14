package com.example.streamingapp.data

data class TvMazeResponse(
    val show: Show
)
data class Show(
    val id: Int,
    val name: String,
    val image: ShowImage?,
    val rating: Rating?,
    val genres: List<String>,
    val summary: String?
)

data class ShowImage(
    val medium: String?,
    val original: String?
)

data class Rating(
    val average: Double?
)