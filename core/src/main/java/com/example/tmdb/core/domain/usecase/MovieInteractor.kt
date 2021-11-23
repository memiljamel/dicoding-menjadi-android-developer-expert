package com.example.tmdb.core.domain.usecase

import com.example.tmdb.core.data.Resource
import com.example.tmdb.core.domain.model.Movie
import com.example.tmdb.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override fun getAllMovie(): Flow<Resource<List<Movie>>> {
        return movieRepository.getAllMovie()
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return movieRepository.getFavoriteMovie()
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        return movieRepository.setFavoriteMovie(movie, state)
    }
}