package com.duhan.movieapp.feature_movie.presentation.slider

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duhan.movieapp.feature_movie.domain.use_case.NowPlayingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor(private val nowPlayingUseCase: NowPlayingUseCase) :
    ViewModel() {
    private val sliderItems = MutableLiveData<List<SliderItem>>()
    var list = mutableListOf<SliderItem>()
    private val compositeDisposable = CompositeDisposable()
    val items: LiveData<List<SliderItem>> = sliderItems
    private var page = 0;

    fun getSliderItems() {
        compositeDisposable.add(nowPlayingUseCase.execute(++page)
            .subscribe({
                list = it as MutableList<SliderItem>
                sliderItems.postValue(list)
            }, {
                // TODO: handle error and show error message
                it.printStackTrace()
            }
            )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}