package com.duhan.movieapp.feature_movie.presentation.list

import com.duhan.movieapp.feature_movie.presentation.MovieItem

interface ItemClickListener {
    fun onClicked(movieItem: MovieItem)
}