package com.duhan.movieapp.feature_movie.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import autodispose2.AutoDispose.autoDisposable
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider
import com.duhan.movieapp.databinding.ListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ListFragmentBinding.inflate(inflater)
        binding.adapter = ListAdapter()
        viewModel.getItems()
            .to(autoDisposable(AndroidLifecycleScopeProvider.from(this@ListFragment)))
            .subscribe({
                lifecycleScope.launch {
                    binding.adapter?.submitData(it)
                }
            }, {

            })
        return binding.root
    }

}