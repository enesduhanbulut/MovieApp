package com.duhan.movieapp.feature_movie.presentation

import android.os.Parcel
import android.os.Parcelable

data class MovieItem(
    val id: Int,
    val title: String?,
    val imageUrl: String?,
    val description: String?,
    val releaseDate: String?,
    val rate: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
        parcel.writeString(releaseDate)
        parcel.writeString(rate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieItem> {
        override fun createFromParcel(parcel: Parcel): MovieItem {
            return MovieItem(parcel)
        }

        override fun newArray(size: Int): Array<MovieItem?> {
            return arrayOfNulls(size)
        }
    }
}