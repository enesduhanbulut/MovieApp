package com.duhan.movieapp.feature_movie.data.domain

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.duhan.movieapp.feature_movie.data.data_source.network.MovieAPIService
import com.duhan.movieapp.feature_movie.data.data_source.network.model.UpcomingResult
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single

private const val PAGE_INDEX = 1

class UpcomingPagingSource(
    private val service: MovieAPIService,
) : RxPagingSource<Int, UpcomingResult>() {

    override fun getRefreshKey(state: PagingState<Int, UpcomingResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    private fun toLoadResult(
        response: List<UpcomingResult>,
        page: Int
    ): LoadResult<Int, UpcomingResult> {
        return LoadResult.Page(
            response,
            null,  // Only paging forward.
            if (response.isEmpty()) null else page + 1,
            LoadResult.Page.COUNT_UNDEFINED,
            LoadResult.Page.COUNT_UNDEFINED
        )
    }

    override fun loadSingle(params: LoadParams<Int>): @NonNull Single<LoadResult<Int, UpcomingResult>> {
        val page = params.key ?: PAGE_INDEX

        return service.getUpcoming(page)
            .map { it.results }
            .map { toLoadResult(it, page) }
            .onErrorReturn { LoadResult.Error(it) }
    }
}
