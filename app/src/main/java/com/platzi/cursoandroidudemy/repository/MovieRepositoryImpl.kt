package com.platzi.cursoandroidudemy.repository

import com.platzi.cursoandroidudemy.core.InternetCheck
import com.platzi.cursoandroidudemy.data.model.remote.MovieList
import com.platzi.cursoandroidudemy.data.model.remote.RemoteMovieDataSource
import com.platzi.cursoandroidudemy.data.model.remote.local.LocalMovieDataSource
import com.platzi.cursoandroidudemy.data.model.remote.toMovieEntity

class MovieRepositoryImpl(
    private val dataSourceRemote: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {


    override suspend fun getUpcomingMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable() as Boolean) {
            dataSourceRemote.getUpcomingMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
            }
            dataSourceLocal.getUpcomingMovies()
        } else {
            dataSourceLocal.getUpcomingMovies()
        }
    }

    override suspend fun getTopRatedMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable() as Boolean) {
            dataSourceRemote.getTopRatedMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
            }
            dataSourceLocal.getTopRatedMovies()
        } else {
            dataSourceLocal.getTopRatedMovies()
        }
    }

    override suspend fun getPopularMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable() as Boolean) {
            dataSourceRemote.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
            }
            return dataSourceLocal.getPopularMovies()
        } else {
            return dataSourceLocal.getPopularMovies()
        }
    }
}