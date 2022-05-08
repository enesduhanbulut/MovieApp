package com.duhan.movieapp.feature_movie.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.duhan.movieapp.feature_movie.domain.use_case.UpcomingUseCase
import com.duhan.movieapp.feature_movie.presentation.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(private val upcomingUseCase: UpcomingUseCase) :
    ViewModel() {
    private lateinit var flow: Flowable<PagingData<MovieItem>>

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getItems(): Flowable<PagingData<MovieItem>> {
        if (!::flow.isInitialized) {
            flow = upcomingUseCase.execute()
            flow.cachedIn(viewModelScope)
        }
        return flow
    }


}