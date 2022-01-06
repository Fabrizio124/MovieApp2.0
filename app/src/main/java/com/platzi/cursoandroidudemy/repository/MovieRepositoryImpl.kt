package com.platzi.cursoandroidudemy.repository

import com.platzi.cursoandroidudemy.data.model.remote.MovieDataSource
import com.platzi.cursoandroidudemy.data.model.remote.MovieList

class MovieRepositoryImpl(private val dataSource: MovieDataSource): MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList = dataSource.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()

}