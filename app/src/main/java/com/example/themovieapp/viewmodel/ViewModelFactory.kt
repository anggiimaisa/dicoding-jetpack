package com.example.themovieapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themovieapp.di.Injection
import com.example.themovieapp.ui.MovieFavorite.MovieFavoriteRepository
import com.example.themovieapp.ui.MovieFavorite.MovieFavoriteViewModel
import com.example.themovieapp.ui.detail.DetailMovieRepository
import com.example.themovieapp.ui.detail.DetailMovieViewModel
import com.example.themovieapp.ui.movie.MovieRepository
import com.example.themovieapp.ui.movie.MovieViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(movieRepository: MovieRepository, detailMovieRepository: DetailMovieRepository, movieFavoriteRepository: MovieFavoriteRepository) : ViewModelProvider.NewInstanceFactory() {

    val movieRepository = movieRepository
    val detailMovieRepository = detailMovieRepository
    val movieFavoriteRepository = movieFavoriteRepository

    companion object {
        @Volatile
        var INSTANCE : ViewModelFactory? = null

        fun getInstance(application: Application) : ViewModelFactory {

            if (INSTANCE == null)
                synchronized(ViewModelFactory::class) {
                    if (INSTANCE == null)
                        INSTANCE = ViewModelFactory(Injection.provideMovieRepository(), Injection.provideDetailMovieRepository(), Injection.provideMovieFavoriteRepository(application))
                }
            return INSTANCE!!
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java))
            return MovieViewModel(movieRepository) as T
        else if (modelClass.isAssignableFrom(DetailMovieViewModel::class.java))
            return DetailMovieViewModel(detailMovieRepository) as T
        else if (modelClass.isAssignableFrom(MovieFavoriteViewModel::class.java))
            return MovieFavoriteViewModel(movieFavoriteRepository) as T

        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }
}