package com.example.themovieapp.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.TVShow

class MovieViewModel(movieRepository: MovieRepository) : ViewModel() {

    val movieRepository = movieRepository

    fun getCurrentMovies() : LiveData<List<Movie>> {
        return movieRepository.getMovies()
    }

    fun getCurrentTVShows() : LiveData<List<TVShow>> {
        return movieRepository.getTVShows()
    }


}