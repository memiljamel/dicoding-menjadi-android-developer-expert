package com.example.tmdb.core.data.source.remote.network

import com.example.tmdb.core.BuildConfig
import com.example.tmdb.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie/now_playing?api_key=${BuildConfig.API_KEY}")
    suspend fun getNowPlaying(): ListMovieResponse
}