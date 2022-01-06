package com.platzi.cursoandroidudemy.ui.movie.moviedtails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import com.platzi.cursoandroidudemy.R
import com.platzi.cursoandroidudemy.core.Resource
import com.platzi.cursoandroidudemy.data.model.remote.MovieDataSource
import com.platzi.cursoandroidudemy.databinding.FragmentMovieBinding
import com.platzi.cursoandroidudemy.presentation.MovieViewModel
import com.platzi.cursoandroidudemy.presentation.MovieViewModelFactory
import com.platzi.cursoandroidudemy.repository.MovieRepositoryImpl
import com.platzi.cursoandroidudemy.repository.RetrofitClient

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private lateinit var  binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                MovieDataSource(RetrofitClient.webservice)
            )
        )
    }
    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is Resource.Loading -> {
                    Log.d("LiveData" , "Loading... ")
                }
                is Resource.Succes -> {
                    Log.d("LiveData", "Upcoming: ${result.data.first} \n \n TopRated: ${result.data.second} \n \n Popular: ${result.data.third}")
                }
                is Resource.Failure -> {
                    Log.d("Error", "${result.exception}")
                }
            }

        })

    }
}