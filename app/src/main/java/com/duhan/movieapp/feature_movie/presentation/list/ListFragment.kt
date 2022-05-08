package com.duhan.movieapp.feature_movie.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import autodispose2.AutoDispose.autoDisposable
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider
import com.duhan.movieapp.R
import com.duhan.movieapp.databinding.ListFragmentBinding
import com.duhan.movieapp.feature_movie.presentation.MovieItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()
    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ListFragmentBinding.inflate(inflater)
        val itemClickListener: ItemClickListener = object : ItemClickListener {
            override fun onClicked(movieItem: MovieItem) {

                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_mainFragment_to_detailFragment, Bundle().apply {
                        putParcelable("movieItem", movieItem)
                    })
            }
        }
        if (rootView == null) {
            rootView = binding.root
            binding.adapter = ListAdapter(itemClickListener)
            viewModel.getItems()
                .to(autoDisposable(AndroidLifecycleScopeProvider.from(activity)))
                .subscribe({
                    lifecycleScope.launch {
                        binding.adapter?.submitData(it)
                    }
                }, {

                })

        }
        return rootView as View
    }

}