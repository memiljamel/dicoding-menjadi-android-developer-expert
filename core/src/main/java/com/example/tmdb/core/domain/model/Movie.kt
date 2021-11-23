package com.example.tmdb.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val poster: String,
    val language: String,
    val title: String,
    val overview: String,
    val release: String,
    val vote: Double,
    val isFavorite: Boolean
) : Parcelable