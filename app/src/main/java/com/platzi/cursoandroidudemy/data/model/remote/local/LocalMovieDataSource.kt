package com.platzi.cursoandroidudemy.data.model.remote.local

import com.platzi.cursoandroidudemy.data.model.remote.MovieEntity
import com.platzi.cursoandroidudemy.data.model.remote.MovieList
import com.platzi.cursoandroidudemy.data.model.remote.toMovieList

class LocalMovieDataSource(private val movieDao: MovieDao) {

    suspend fun getUpcomingMovies(): MovieList {
        return movieDao.getAllMovies().filter{it.movie_type == "upcoming"}.toMovieList()
    }

    suspend fun getTopRatedMovies(): MovieList {
        return movieDao.getAllMovies().filter{it.movie_type == "toprated"}.toMovieList()

    }

    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter{it.movie_type == "popular"}.toMovieList()
    }

    suspend fun saveMovie(movie: MovieEntity){
        movieDao.saveMovie(movie)
    }
}