package com.duhan.movieapp.feature_movie.data.domain

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.duhan.movieapp.feature_movie.data.data_source.network.MovieAPIService
import com.duhan.movieapp.feature_movie.presentation.list.ListItem
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single

private const val PAGE_INDEX = 1

class UpcomingPagingSource(
    private val service: MovieAPIService,
) : RxPagingSource<Int, ListItem>() {

    override fun getRefreshKey(state: PagingState<Int, ListItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    private fun toLoadResult(
        response: List<ListItem>,
        page: Int
    ): LoadResult<Int, ListItem> {
        return LoadResult.Page(
            response,
            null,  // Only paging forward.
            if (response.isEmpty()) null else page + 1,
            LoadResult.Page.COUNT_UNDEFINED,
            LoadResult.Page.COUNT_UNDEFINED
        )
    }

    override fun loadSingle(params: LoadParams<Int>): @NonNull Single<LoadResult<Int, ListItem>> {
        val page = params.key ?: PAGE_INDEX

        return service.getUpcoming(page)
            .map {
                it.results
            }
            .map { it ->
                it.map {
                    ListItem(
                        it.id!!,
                        it.title,
                        it.backdropPath,
                        it.overview,
                        it.releaseDate
                    )
                }
            }
            .map { toLoadResult(it, page) }
            .onErrorReturn {
                LoadResult.Error(it) }
    }
}
