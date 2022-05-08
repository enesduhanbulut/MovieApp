package com.duhan.movieapp.feature_movie.presentation.slider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.duhan.movieapp.R
import com.smarteist.autoimageslider.SliderView
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSliderItems()
        val sliderView = view.findViewById<SliderView>(R.id.imageSlider)
        val adapter = SliderAdapter()
        sliderView.setSliderAdapter(adapter)
        viewModel.items.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.addData(it)
                if (adapter.count < 10) {
                    viewModel.getSliderItems()
                }

            }
        }

    }
}