package com.example.themovieapp.di

import android.app.Application
import com.example.themovieapp.ui.MovieFavorite.MovieFavoriteRepository
import com.example.themovieapp.ui.detail.DetailMovieRepository
import com.example.themovieapp.ui.movie.MovieRepository

class Injection {

    companion object {
        fun provideMovieRepository() : MovieRepository {
            return MovieRepository.getInstance()
        }
        fun provideMovieFavoriteRepository(application: Application) : MovieFavoriteRepository {
            return MovieFavoriteRepository.getInstance(application)
        }
        fun provideDetailMovieRepository() : DetailMovieRepository {
            return DetailMovieRepository.getInstance()
        }
    }

}