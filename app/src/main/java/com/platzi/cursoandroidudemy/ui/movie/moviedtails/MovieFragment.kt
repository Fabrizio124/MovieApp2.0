package com.platzi.cursoandroidudemy.ui.movie.moviedtails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.platzi.cursoandroidudemy.R
import com.platzi.cursoandroidudemy.core.Resource
import com.platzi.cursoandroidudemy.data.model.remote.Movie
import com.platzi.cursoandroidudemy.data.model.remote.RemoteMovieDataSource
import com.platzi.cursoandroidudemy.data.model.remote.local.AppDatabase
import com.platzi.cursoandroidudemy.data.model.remote.local.LocalMovieDataSource
import com.platzi.cursoandroidudemy.databinding.FragmentMovieBinding
import com.platzi.cursoandroidudemy.presentation.MovieViewModel
import com.platzi.cursoandroidudemy.presentation.MovieViewModelFactory
import com.platzi.cursoandroidudemy.repository.MovieRepositoryImpl
import com.platzi.cursoandroidudemy.repository.RetrofitClient
import com.platzi.cursoandroidudemy.ui.movie.moviedtails.adapters.concat.MovieAdapter
import com.platzi.cursoandroidudemy.ui.movie.moviedtails.adapters.concat.PopularConcatAdapter
import com.platzi.cursoandroidudemy.ui.movie.moviedtails.adapters.concat.TopRatedConcatAdapter
import com.platzi.cursoandroidudemy.ui.movie.moviedtails.adapters.concat.UpcomingConcatAdapter

class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var  binding: FragmentMovieBinding
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                RemoteMovieDataSource(RetrofitClient.webservice),
                LocalMovieDataSource(AppDatabase.getDatabase(requireContext()).movieDao())
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
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Succes -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    result.data.first.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    result.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(2,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    result.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )
                    }

                    binding.rvMovies.adapter = concatAdapter
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Error", "${result.exception}")
                }
            }

        })

    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.backdrop_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }
}