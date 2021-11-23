package com.example.tmdb.detail

import androidx.lifecycle.ViewModel
import com.example.tmdb.core.domain.model.Movie
import com.example.tmdb.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        return movieUseCase.setFavoriteMovie(movie, newState)
    }
}