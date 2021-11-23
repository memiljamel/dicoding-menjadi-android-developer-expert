package com.example.tmdb.core.utils

import com.example.tmdb.core.data.source.local.entity.MovieEntity
import com.example.tmdb.core.data.source.remote.response.MovieResponse
import com.example.tmdb.core.domain.model.Movie

object DataMapper {
    fun mapResponseToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                poster = it.poster,
                language = it.language,
                title = it.title,
                overview = it.overview,
                release = it.release,
                vote = it.vote,
                isFavorite = false
            )
            movieList.add(movie)
        }

        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
        return input.map {
            Movie(
                id = it.id,
                poster = it.poster,
                language = it.language,
                title = it.title,
                overview = it.overview,
                release = it.release,
                vote = it.vote,
                isFavorite = it.isFavorite
            )
        }
    }

    fun mapDomainToEntity(input: Movie): MovieEntity {
        return MovieEntity(
            id = input.id,
            poster = input.poster,
            language = input.language,
            title = input.title,
            overview = input.overview,
            release = input.release,
            vote = input.vote,
            isFavorite = input.isFavorite
        )
    }
}