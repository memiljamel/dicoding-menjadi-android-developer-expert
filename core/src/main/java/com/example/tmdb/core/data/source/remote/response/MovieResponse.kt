package com.example.tmdb.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster_path")
    val poster: String,

    @field:SerializedName("original_language")
    val language: String,

    @field:SerializedName("original_title")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date")
    val release: String,

    @field:SerializedName("vote_average")
    val vote: Double
)