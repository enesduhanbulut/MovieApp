package com.duhan.movieapp.feature_movie.data.data_source.network.model

import com.duhan.movieapp.feature_movie.data.data_source.network.MovieAPIService
import org.junit.Before
import org.junit.Test

class NowPlayingResultTest {
    lateinit var movieAPIService: MovieAPIService

    @Before
    fun setUp() {
        movieAPIService = MovieAPIService.create()
    }

    @Test
    fun getNowPlayingTest() {
        val size = movieAPIService.getNowPlaying(1)
            .blockingGet().results.size
        assert(size > 0)


    }
}