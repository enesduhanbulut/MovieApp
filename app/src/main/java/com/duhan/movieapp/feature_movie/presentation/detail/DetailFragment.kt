package com.duhan.movieapp.feature_movie.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.duhan.movieapp.R
import com.duhan.movieapp.databinding.DetailFragmentBinding
import com.duhan.movieapp.databinding.ListFragmentBinding
import com.duhan.movieapp.feature_movie.presentation.MovieItem
import com.duhan.movieapp.feature_movie.presentation.list.ItemClickListener
import com.duhan.movieapp.feature_movie.presentation.list.ListAdapter

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailFragmentBinding.inflate(inflater)
        binding.item = arguments?.get("movieItem") as MovieItem
        return binding.root
    }

}