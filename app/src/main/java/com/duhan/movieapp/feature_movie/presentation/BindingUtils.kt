package com.duhan.movieapp.feature_movie.presentation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context).asBitmap().load("https://image.tmdb.org/t/p/original$url").fitCenter().into(view)
    }
}

