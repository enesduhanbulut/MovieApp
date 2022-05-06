package com.duhan.movieapp.feature_movie.data.data_source.network.model

import com.google.gson.annotations.SerializedName

data class APIResponse<T>(

    @SerializedName("dates") var dates: Dates? = Dates(),
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<T> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null

)