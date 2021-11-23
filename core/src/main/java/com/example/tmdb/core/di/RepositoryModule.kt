package com.example.tmdb.core.di

import com.example.tmdb.core.data.MovieRepository
import com.example.tmdb.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository): IMovieRepository
}