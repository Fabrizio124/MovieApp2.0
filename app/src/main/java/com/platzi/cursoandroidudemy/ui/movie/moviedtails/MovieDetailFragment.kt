package com.platzi.cursoandroidudemy.ui.movie.moviedtails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.platzi.cursoandroidudemy.R
import com.platzi.cursoandroidudemy.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailBinding.bind(view)
    }

}