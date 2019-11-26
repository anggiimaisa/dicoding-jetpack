package com.example.themovieapp.ui.MovieFavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.themovieapp.data.Movie
import com.example.themovieapp.data.TVShow

class MovieFavoriteViewModel(movieFavoriteRepository: MovieFavoriteRepository) : ViewModel() {

    val movieFavoriteRepository = movieFavoriteRepository

    fun insertMovieFavorite(movieFavorite: Movie) {
        movieFavoriteRepository.insertMovieFavorite(movieFavorite)
    }

    fun insertTVShowFavorite(tvShow: TVShow) {
        movieFavoriteRepository.insertTVShowFavorite(tvShow)
    }

    fun deleteMovieFavorite(movieId: Int) {
        movieFavoriteRepository.deleteMovieFavorite(movieId)
    }

    fun deleteTVShowFavorite(tvShowId: Int) {
        movieFavoriteRepository.deleteTVShowFavorite(tvShowId)
    }

    fun getMovieFavoriteById(movieId: Int) : LiveData<Movie> {
        return movieFavoriteRepository.getMovieFavorite(movieId)
    }

    fun getTVShowFavoriteById(tvShowId: Int) : LiveData<TVShow> {
        return movieFavoriteRepository.getTVShowFavorite(tvShowId)
    }

    val getMoviesFavorite : LiveData<PagedList<Movie>> = movieFavoriteRepository.allMoviesFavorite()
    val getTVShowFavorite : LiveData<PagedList<TVShow>> = movieFavoriteRepository.allTVShowsFavorite()

}