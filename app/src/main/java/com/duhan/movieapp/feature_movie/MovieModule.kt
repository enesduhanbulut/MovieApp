package com.duhan.movieapp.feature_movie

import com.duhan.movieapp.feature_movie.data.data_source.network.MovieAPIService
import com.duhan.movieapp.feature_movie.data.domain.PagingSources
import com.duhan.movieapp.feature_movie.data.domain.UpcomingPagingSource
import com.duhan.movieapp.feature_movie.domain.repository.MovieRepository
import com.duhan.movieapp.feature_movie.domain.repository.MovieRepositoryImpl
import com.duhan.movieapp.feature_movie.domain.use_case.NowPlayingUseCase
import com.duhan.movieapp.feature_movie.domain.use_case.UpcomingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

class MovieModule {
    @Provides
    fun provideUpcomingPagingSource(movieAPIService: MovieAPIService): UpcomingPagingSource {
        return UpcomingPagingSource(movieAPIService)
    }

    @Provides
    fun providePagingSources(
        upcomingPagingSource: UpcomingPagingSource
    ): PagingSources {
        return PagingSources(upcomingPagingSource)
    }

    @Provides
    fun provideMovieRepository(
        pagingSources: PagingSources,
        movieAPIService: MovieAPIService
    ): MovieRepository {
        return MovieRepositoryImpl(pagingSources, movieAPIService)
    }

    @Provides
    fun provideNowPlayingUseCase(movieRepository: MovieRepository): NowPlayingUseCase {
        return NowPlayingUseCase(movieRepository)
    }

    @Provides
    fun provideUpcomingUseCase(movieRepository: MovieRepository): UpcomingUseCase {
        return UpcomingUseCase(movieRepository)
    }
}