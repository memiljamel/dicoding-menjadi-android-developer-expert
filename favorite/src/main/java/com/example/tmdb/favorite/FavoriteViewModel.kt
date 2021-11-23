package com.example.tmdb.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.tmdb.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favorite = movieUseCase.getFavoriteMovie().asLiveData()
}