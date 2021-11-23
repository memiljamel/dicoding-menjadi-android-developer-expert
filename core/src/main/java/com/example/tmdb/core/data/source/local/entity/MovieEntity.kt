package com.example.tmdb.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "poster_path")
    var poster: String,

    @ColumnInfo(name = "original_language")
    var language: String,

    @ColumnInfo(name = "original_title")
    var title: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "release_date")
    var release: String,

    @ColumnInfo(name = "vote_average")
    var vote: Double,

    @ColumnInfo(name = "favorite")
    var isFavorite: Boolean = false
)