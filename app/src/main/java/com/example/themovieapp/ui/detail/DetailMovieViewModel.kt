package com.example.themovieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.TVShow

class DetailMovieViewModel(detailMovieRepository: DetailMovieRepository) : ViewModel() {

    val detailMovieRepository = detailMovieRepository
    var movieId: Int = 0

    fun getCurrentDetailMovie() : LiveData<Movie> {
        return detailMovieRepository.getMovieById(movieId)!!
    }

    fun getCurrentDetailTVShow() : LiveData<TVShow> {
        return detailMovieRepository.getTvShowById(movieId)!!
    }

    fun setId(movieId: Int) {
        this.movieId = movieId
    }

}