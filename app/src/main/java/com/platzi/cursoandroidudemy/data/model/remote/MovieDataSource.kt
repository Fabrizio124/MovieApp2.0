package com.platzi.cursoandroidudemy.data.model.remote

import com.platzi.cursoandroidudemy.application.AppConstants
import com.platzi.cursoandroidudemy.repository.WebService


class MovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)

}