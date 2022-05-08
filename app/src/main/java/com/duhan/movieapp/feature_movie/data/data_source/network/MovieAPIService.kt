package com.duhan.movieapp.feature_movie.data.data_source.network

import com.duhan.movieapp.BuildConfig
import com.duhan.movieapp.feature_movie.data.data_source.network.model.APIResponse
import com.duhan.movieapp.feature_movie.data.data_source.network.model.NowPlayingResult
import com.duhan.movieapp.feature_movie.data.data_source.network.model.UpcomingResult
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPIService {
    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("page") page: Int,
    ): Single<APIResponse<NowPlayingResult>>

    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("page") page: Int
    ): Single<APIResponse<UpcomingResult>>

    companion object {

        fun create(): MovieAPIService {
            val logger = HttpLoggingInterceptor().apply {
                level =
                    HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(AuthenticationInterceptor(BuildConfig.API_KEY))
                .build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL + "/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(
                    RxJava3CallAdapterFactory
                        .createWithScheduler(Schedulers.io())
                )
                .build()
                .create(MovieAPIService::class.java)
        }
    }

}