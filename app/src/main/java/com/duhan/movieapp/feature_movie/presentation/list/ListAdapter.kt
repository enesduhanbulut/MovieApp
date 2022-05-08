package com.duhan.movieapp.feature_movie.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.duhan.movieapp.databinding.ListItemViewBinding
import com.duhan.movieapp.feature_movie.presentation.MovieItem


class ListAdapter(private val itemClickListener: ItemClickListener) :
    PagingDataAdapter<MovieItem, ListAdapter.ViewHolder>(
        diffCallback
    ) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: ListItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieItem) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemViewBinding.inflate(inflater)
        binding.itemOnClick = itemClickListener
        return ViewHolder(binding)
    }

}