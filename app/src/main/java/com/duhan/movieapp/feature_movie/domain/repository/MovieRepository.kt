package com.duhan.movieapp.feature_movie.domain.repository

import androidx.paging.PagingData
import com.duhan.movieapp.feature_movie.data.data_source.network.model.NowPlayingResult
import com.duhan.movieapp.feature_movie.data.data_source.network.model.UpcomingResult
import io.reactivex.rxjava3.core.Flowable

interface MovieRepository {
    fun getNowPlaying(): Flowable<PagingData<NowPlayingResult>>
    fun getUpcoming(): Flowable<PagingData<UpcomingResult>>
}
