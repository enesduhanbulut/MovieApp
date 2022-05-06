package com.duhan.movieapp.feature_movie

import com.duhan.movieapp.feature_movie.data.data_source.network.MovieAPIService
import com.duhan.movieapp.feature_movie.data.domain.NowPlayingPagingSource
import com.duhan.movieapp.feature_movie.data.domain.PagingSources
import com.duhan.movieapp.feature_movie.data.domain.UpcomingPagingSource
import com.duhan.movieapp.feature_movie.domain.repository.MovieRepository
import com.duhan.movieapp.feature_movie.domain.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

class MovieModule {
    @Provides
    fun provideUpcomingPagingSource(movieAPIService: MovieAPIService): NowPlayingPagingSource {
        return NowPlayingPagingSource(movieAPIService)
    }

    @Provides
    fun provideNowPlayingPagingSource(movieAPIService: MovieAPIService): NowPlayingPagingSource {
        return NowPlayingPagingSource(movieAPIService)
    }

    @Provides
    fun providePagingSources(
        upcomingPagingSource: UpcomingPagingSource,
        nowPlayingPagingSource: NowPlayingPagingSource
    ): PagingSources {
        return PagingSources(upcomingPagingSource, nowPlayingPagingSource)
    }

    @Provides
    fun provideMovieRepository(pagingSources: PagingSources): MovieRepository {
        return MovieRepositoryImpl(pagingSources)
    }

}