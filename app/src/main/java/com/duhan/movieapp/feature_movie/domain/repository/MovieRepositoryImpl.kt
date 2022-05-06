package com.duhan.movieapp.feature_movie.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.duhan.movieapp.feature_movie.data.data_source.network.model.NowPlayingResult
import com.duhan.movieapp.feature_movie.data.data_source.network.model.UpcomingResult
import com.duhan.movieapp.feature_movie.data.domain.PagingSources
import io.reactivex.rxjava3.core.Flowable

class MovieRepositoryImpl(var pagingSources: PagingSources) : MovieRepository {
    private val pagingConfig = PagingConfig(20)

    override fun getNowPlaying(): Flowable<PagingData<NowPlayingResult>> {
        return Pager(pagingConfig) {
            pagingSources.nowPlayingPagingSource
        }.flowable
    }

    override fun getUpcoming(): Flowable<PagingData<UpcomingResult>> {
        return Pager(pagingConfig) {
            pagingSources.upcomingPagingSource
        }.flowable
    }


}