package com.duhan.movieapp.feature_movie.data.data_source.network.model
import com.google.gson.annotations.SerializedName
data class Dates(
    @SerializedName("maximum") var maximum: String? = null,
    @SerializedName("minimum") var minimum: String? = null

)