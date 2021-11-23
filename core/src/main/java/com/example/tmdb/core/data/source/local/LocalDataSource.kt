package com.example.tmdb.core.data.source.local

import com.example.tmdb.core.data.source.local.entity.MovieEntity
import com.example.tmdb.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    fun getAllMovie(): Flow<List<MovieEntity>> {
        return movieDao.getAllMovie()
    }

    fun getFavoriteMovie(): Flow<List<MovieEntity>> {
        return movieDao.getFavoriteMovie()
    }

    suspend fun insertMovie(movie: List<MovieEntity>) {
        return movieDao.insertMovie(movie)
    }

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}