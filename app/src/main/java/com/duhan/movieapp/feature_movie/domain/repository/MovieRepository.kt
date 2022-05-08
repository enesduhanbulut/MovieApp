package com.duhan.movieapp.feature_movie.domain.repository

import androidx.paging.PagingData
import com.duhan.movieapp.feature_movie.data.data_source.network.model.NowPlayingResult
import com.duhan.movieapp.feature_movie.presentation.MovieItem
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface MovieRepository {
    fun getNowPlaying(page: Int): Single<ArrayList<NowPlayingResult>>
    fun getUpcoming(): Flowable<PagingData<MovieItem>>
}
