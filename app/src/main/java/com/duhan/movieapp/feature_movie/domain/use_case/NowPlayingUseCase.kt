package com.duhan.movieapp.feature_movie.domain.use_case

import androidx.paging.PagingData
import com.duhan.movieapp.feature_movie.data.data_source.network.model.NowPlayingResult
import com.duhan.movieapp.feature_movie.domain.repository.MovieRepository
import io.reactivex.rxjava3.core.Flowable

class NowPlayingUseCase(private val repository: MovieRepository) {
    fun execute(): Flowable<PagingData<NowPlayingResult>> {
        return repository.getNowPlaying()
    }
}