package com.platzi.cursoandroidudemy.repository

import com.platzi.cursoandroidudemy.data.model.remote.MovieList

interface MovieRepository {
    suspend fun getUpcomingMovies(): MovieList
    suspend fun getTopRatedMovies(): MovieList
    suspend fun getPopularMovies(): MovieList
}