package com.duhan.movieapp.feature_movie.presentation.slider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.duhan.movieapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SliderFragment : Fragment() {

    private val viewModel: SliderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.slider_fragment, container, false)
    }

}