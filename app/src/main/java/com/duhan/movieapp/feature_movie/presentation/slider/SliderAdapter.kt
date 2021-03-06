package com.duhan.movieapp.feature_movie.presentation.slider

import android.view.LayoutInflater
import android.view.ViewGroup
import com.duhan.movieapp.databinding.SliderItemViewBinding
import com.duhan.movieapp.feature_movie.presentation.MovieItem
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapter() :
    SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
    private var items = mutableListOf<MovieItem>()
    override fun getCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapterVH {
        val inflater = LayoutInflater.from(parent?.context)
        val binding = SliderItemViewBinding.inflate(inflater)
        return SliderAdapterVH(binding)
    }

    override fun onBindViewHolder(holder: SliderAdapterVH, position: Int) =
        holder.bind(items[position])

    fun addData(data: List<MovieItem>) {
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class SliderAdapterVH(private val binding: SliderItemViewBinding) :
        SliderViewAdapter.ViewHolder(binding.root) {
        fun bind(item: MovieItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

}