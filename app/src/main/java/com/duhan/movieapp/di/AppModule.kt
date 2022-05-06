package com.duhan.movieapp.di

import com.duhan.movieapp.feature_movie.data.data_source.network.MovieAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieAPIService() = MovieAPIService.create()


}
