package com.duhan.movieapp.feature_movie.data.domain

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.duhan.movieapp.feature_movie.data.data_source.network.MovieAPIService
import com.duhan.movieapp.feature_movie.presentation.MovieItem
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single

private const val PAGE_INDEX = 1

class UpcomingPagingSource(
    private val service: MovieAPIService,
) : RxPagingSource<Int, MovieItem>() {

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    private fun toLoadResult(
        response: List<MovieItem>,
        page: Int
    ): LoadResult<Int, MovieItem> {
        return LoadResult.Page(
            response,
            null,  // Only paging forward.
            if (response.isEmpty()) null else page + 1,
            LoadResult.Page.COUNT_UNDEFINED,
            LoadResult.Page.COUNT_UNDEFINED
        )
    }

    override fun loadSingle(params: LoadParams<Int>): @NonNull Single<LoadResult<Int, MovieItem>> {
        val page = params.key ?: PAGE_INDEX

        return service.getUpcoming(page)
            .map {
                it.results
            }
            .map { it ->
                it.map {
                    MovieItem(
                        it.id!!,
                        it.title,
                        it.backdropPath,
                        it.overview,
                        it.releaseDate,
                        it.voteAverage.toString()
                    )
                }
            }
            .map { toLoadResult(it, page) }
            .onErrorReturn {
                LoadResult.Error(it) }
    }
}
