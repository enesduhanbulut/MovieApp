package com.duhan.movieapp.feature_movie.presentation.list

data class ListItem(
    val id: Int,
    val title: String?,
    val imageUrl: String?,
    val description: String?,
    val releaseDate: String?
)