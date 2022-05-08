package com.duhan.movieapp.feature_movie.domain.use_case

import androidx.paging.PagingData
import com.duhan.movieapp.feature_movie.data.data_source.network.model.UpcomingResult
import com.duhan.movieapp.feature_movie.domain.repository.MovieRepository
import com.duhan.movieapp.feature_movie.presentation.list.ListItem
import io.reactivex.rxjava3.core.Flowable

class UpcomingUseCase(var repository: MovieRepository) {
    fun execute(): Flowable<PagingData<ListItem>> {
        return repository.getUpcoming()
    }
}