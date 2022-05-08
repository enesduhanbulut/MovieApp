package com.duhan.movieapp.feature_movie.domain.use_case

import com.duhan.movieapp.feature_movie.domain.daysBetween
import com.duhan.movieapp.feature_movie.domain.repository.MovieRepository
import com.duhan.movieapp.feature_movie.presentation.MovieItem
import io.reactivex.rxjava3.core.Single
import java.util.*

class NowPlayingUseCase(private val repository: MovieRepository) {
    fun execute(page: Int): Single<List<MovieItem>> {
        return repository.getNowPlaying(page)
            .map { it ->
                it.filter {
                    daysBetween(
                        it.releaseDate!!,
                        Calendar.getInstance()
                    ) < 30
                }.filter { it.backdropPath != null }
            }
            .map { it ->
                it.map {
                    MovieItem(
                        it.id!!,
                        it.title!!,
                        it.backdropPath,
                        it.overview!!,
                        it.releaseDate,
                        it.voteAverage.toString()
                    )
                }
            }
    }
}